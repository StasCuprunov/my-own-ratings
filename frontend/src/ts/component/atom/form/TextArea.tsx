import {FunctionComponent} from "react";

export const TextArea: FunctionComponent<any> = ({props}) => {
    return (
        <textarea
            required={props.required}
            name={props.name}
            maxLength={props.maxLength}
            value={props.value}
            onChange={props.onChange}
        />
    );
};