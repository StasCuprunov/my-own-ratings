import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {TextArea} from "../../atom/form/TextArea";
import {InputError} from "../../atom/form/input/InputError";
import {CSS_CLASS_FORM_ATTRIBUTE} from "../../../constant/CSSClassNameConstants";

export const FormForTextArea: FunctionComponent<any> = ({props}) => {
    return (
        <div className={CSS_CLASS_FORM_ATTRIBUTE}>
            <Label props={props.label}/>
            <TextArea props={props.textArea}/>
            {props.inputError &&
                <InputError props={props.inputError}/>
            }
        </div>
    )
};