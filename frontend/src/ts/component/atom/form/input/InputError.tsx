import {FunctionComponent} from "react";

export const InputError: FunctionComponent<any> = ({props}) => {
    return(
        <div>
            {props.condition &&
                <span>{props.text}</span>
            }
        </div>
    );
};