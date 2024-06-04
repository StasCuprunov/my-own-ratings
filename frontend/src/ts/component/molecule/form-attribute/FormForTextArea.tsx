import {FunctionComponent} from "react";
import {Label} from "../../atom/form/Label";
import {TextArea} from "../../atom/form/TextArea";
import {InputError} from "../../atom/form/input/InputError";

export const FormForTextArea: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            <Label props={props.label}/>
            <TextArea props={props.textArea}/>
            {props.inputError &&
                <InputError props={props.inputError}/>
            }
        </div>
    )
};