/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { SetUmlThemeAction, UmlTheme } from '@borkdominik-biguml/uml-glsp/lib/features/theme';
import { inject, injectable, postConstruct } from 'inversify';
import * as vscode from 'vscode';
import { TYPES } from '../../di.types';
import { UVGlspConnector } from '../../glsp/uv-glsp-connector';

@injectable()
export class ThemeIntegration {
    protected readonly disposables: vscode.Disposable[] = [];

    constructor(
        @inject(TYPES.Connector)
        protected readonly connector: UVGlspConnector
    ) {}

    @postConstruct()
    initialize(): void {
        this.refresh();
        this.onChange(e => this.refresh());
    }

    updateTheme(clientId: string): void {
        this.connector.sendActionToClient(clientId, this.createAction());
    }

    refresh(): void {
        this.connector.broadcastActionToClients(this.createAction());
    }

    dispose(): void {
        this.disposables.forEach(d => d.dispose());
    }

    protected onChange(cb: (e: vscode.ColorTheme) => void): void {
        this.disposables.push(vscode.window.onDidChangeActiveColorTheme(cb));
    }

    protected createAction(): SetUmlThemeAction {
        return SetUmlThemeAction.create(mapTheme(vscode.window.activeColorTheme));
    }
}

function mapTheme(theme: vscode.ColorTheme): UmlTheme {
    switch (theme.kind) {
        case vscode.ColorThemeKind.Dark:
        case vscode.ColorThemeKind.HighContrast:
            return 'dark';
        default:
            return 'light';
    }
}
