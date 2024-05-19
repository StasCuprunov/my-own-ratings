import {FunctionComponent} from "react";

export const Input: FunctionComponent<any> = ({props}) => {
    console.log(props)
    return (
        <input
            required={props.required}
            name={props.name}
            type={props.type}
            value={props.value}
            maxLength={props.maxLength}
            pattern={props.pattern}
            onChange={props.onChange}
        />
    );
};