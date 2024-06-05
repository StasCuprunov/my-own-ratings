import {FunctionComponent} from "react";
import {
    getCreateAccountButtonProps
} from "./RegistrationFunctions";
import {CustomButton} from "../component/atom/button/CustomButton";
import {FormForInput} from "../component/molecule/form-attribute/FormForInput";
import {
    CSS_CLASS_CONTAINER,
    CSS_CLASS_FORM_GROUP,
    CSS_CLASS_FORM_REGISTRATION,
    CSS_CLASS_HINTS
} from "../constant/CSSClassNameConstants";
import {getClassNameAttribute} from "../utility/CSSUtility";
import {LinkWithText} from "../component/molecule/LinkWithText";

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({props}) => {
    return (
        <>
            <form onSubmit={props.handleSubmit}
                  className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_FORM_REGISTRATION])}>
                <h1>Registration</h1>
                <div className={CSS_CLASS_FORM_GROUP}>
                    <FormForInput props={props.formForEmail}/>
                </div>
                <div className={CSS_CLASS_FORM_GROUP}>
                    <FormForInput props={props.formForFirstName}/>
                    <FormForInput props={props.formForSurname}/>
                </div>
                <div className={CSS_CLASS_FORM_GROUP}>
                    <FormForInput props={props.formForPassword}/>
                    <FormForInput props={props.formForPasswordConfirmation}/>
                </div>
                <CustomButton props={createAccountButton}/>
                <div>
                    <LinkWithText props={props.loginLink}/>
                </div>
            </form>
            <div className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_HINTS])}>
                <h2>Hints</h2>
                <ul>
                    <li>
                        <sup>1</sup>Required
                    </li>
                </ul>
            </div>
        </>
    );
};
