import {FunctionComponent} from "react";
import {CSS_CLASS_INPUT_ERROR} from "../../../../constant/CSSClassNameConstants";

export const InputError: FunctionComponent<any> = ({props}) => {
    return(
        <div className={CSS_CLASS_INPUT_ERROR}>
            {props.condition &&
                <span>{props.text}</span>
            }
        </div>
    );
};