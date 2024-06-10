import {FunctionComponent} from "react";
import {InputNumber as InputNumberAntDesign
} from 'antd';

export const InputNumber: FunctionComponent<any> = ({props}) => {
    return (
        <InputNumberAntDesign
            required={props.required}
            name={props.name}
            min={props.min}
            max={props.max}
            step={props.step}
            value={props.value}
            onChange={value => props.onChange(value)}
            onBlur={props.onBlur}
            decimalSeparator={"."}
            precision={props.precision}
        />
    );
};