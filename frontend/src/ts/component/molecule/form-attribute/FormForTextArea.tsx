import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {TextArea} from "../../atom/form/TextArea";
import {InputError} from "../../atom/form/input/InputError";
import {CSS_CLASS_FORM_ATTRIBUTE, CSS_CLASS_TEXT_AREA} from "../../../constant/CSSClassNameConstants";
import {getClassNameAttribute} from "../../../utility/CSSUtility";

export const FormForTextArea: FunctionComponent<any> = ({props}) => {
    let className: string = getClassNameAttribute([CSS_CLASS_FORM_ATTRIBUTE, CSS_CLASS_TEXT_AREA]);

    if (props.className) {
        className += " " + props.className;
    }

    return (
        <div className={className}>
            <Label props={props.label}/>
            <TextArea props={props.textArea}/>
            {props.inputError &&
                <InputError props={props.inputError}/>
            }
        </div>
    )
};