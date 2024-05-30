import {ChangeEvent} from "react";

export const handleChange: any = (field: string, setObject: any) => {
    return (e: ChangeEvent<HTMLInputElement>) => {
        setObject((prev: any) => ({
            ...prev,
            [field]: e.target.value
        }));
    };
};

export const getInputTextProps = (name: string, value: string, maxLength: number, handleChange: any,
                                  handleBlur: any): any => {
    return {
        required: true,
        name: name,
        type: "text",
        value: value,
        maxLength: maxLength,
        onChange: handleChange,
        onBlur: handleBlur
    };
};

export const getInputNumberProps = (name: string, required: boolean, min: number, max: number, step: number,
                                    value: number, handleChange: any, handleBlur?: any): any => {
    return {
        name: name,
        required: required,
        type: "number",
        min: min,
        max: max,
        step: step,
        value: value,
        onChange: handleChange,
        onBlur: handleBlur
    };
};