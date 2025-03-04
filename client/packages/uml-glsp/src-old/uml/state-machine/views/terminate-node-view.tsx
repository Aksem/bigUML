/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { CircularNode, IView, RenderingContext, svg } from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class TerminateNodeView implements IView {
    render(node: CircularNode, context: RenderingContext): VNode {
        const imageSrc = require('../../../images/Terminate.svg') as string;
        const terminateNode: any = (
            <g>
                <image class-sprotty-icon={true} href={imageSrc} x={-2} y={-1} width={node.size.width} height={node.size.height} />
            </g>
        );
        return terminateNode;
    }
}
