import {FunctionComponent} from "react";

export const Label: FunctionComponent<any> = ({props}) => {
    return(
        <label htmlFor={props.htmlFor}>
            {props.text}
            {props.sup &&
                <sup>{props.sup}</sup>
            }
        </label>
    );
};