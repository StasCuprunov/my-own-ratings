import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {Input} from "../../atom/form/input/Input";
import {InputError} from "../../atom/form/input/InputError";
import {CSS_CLASS_FORM_ATTRIBUTE} from "../../../constant/CSSClassNameConstants";

export const FormForInput: FunctionComponent<any> = ({props}) => {
    let className: string = CSS_CLASS_FORM_ATTRIBUTE;

    if (props.className) {
        className += " " + props.className;
    }

    return (
        <div className={className}>
            <Label props={props.label}/>
            <Input props={props.input}/>
            {props.inputError &&
                <InputError props={props.inputError}/>
            }
        </div>
    );
};