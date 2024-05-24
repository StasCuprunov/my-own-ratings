import {FunctionComponent} from "react";

export const InputNumber: FunctionComponent<any> = ({props}) => {
    return (
        <input
            required={props.required}
            name={props.name}
            type={"number"}
            min={props.min}
            max={props.max}
            step={props.step}
            value={props.value}
            onChange={props.onChange}
        />
    );
};