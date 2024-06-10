import {FunctionComponent} from "react";
import {InputNumber as InputNumberAntDesign
} from 'antd';

export const InputNumber: FunctionComponent<any> = ({props}) => {
    return (
        <InputNumberAntDesign
            name={props.name}
            step={props.step}
            value={props.value}
            onChange={value => props.onChange(value)}
            onBlur={props.onBlur}
            decimalSeparator={"."}
            precision={props.precision}
            changeOnWheel={props.changeOnWheel}
        />
    );
};