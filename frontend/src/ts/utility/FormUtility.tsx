import {ChangeEvent} from "react";
import DeleteIcon from '@mui/icons-material/Delete';
import CancelIcon from '@mui/icons-material/Cancel';
import {CSS_CLASS_CANCEL_BUTTON_DIALOG, CSS_CLASS_DELETE_BUTTON_DIALOG} from "../constant/CSSClassNameConstants";

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
export const getCancelButtonProps = (handleOnClick: Function) => {
    return {
        text: "Cancel",
        onClick: handleOnClick,
        icon: CancelIcon,
        className: CSS_CLASS_CANCEL_BUTTON_DIALOG
    };
};

export const getDeleteButtonProps = (name: string, handleOnClick: Function) => {
    return {
        text: "Delete " + name,
        onClick: handleOnClick,
        icon: DeleteIcon
    };
};

export const getDeleteButtonInDialogProps = (handleOnClick: Function) => {
    return {
        text: "Delete",
        onClick: handleOnClick,
        icon: DeleteIcon,
        className: CSS_CLASS_DELETE_BUTTON_DIALOG
    };
};

export const getSubmitButtonProps = (props: any) => {
    return {
        ...props,
        type: "submit"
    };
};