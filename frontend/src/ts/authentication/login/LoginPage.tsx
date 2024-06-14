import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {
    getLoginButtonProps
} from "./LoginFunctions";
import {FormForInput} from "../../component/molecule/form-attribute/FormForInput";
import {CSS_CLASS_CONTAINER, CSS_CLASS_FORM_GROUP, CSS_CLASS_FORM_LOGIN} from "../../constant/CSSClassNameConstants";
import {getClassNameAttribute} from "../../utility/CSSUtility";
import {LinkWithText} from "../../component/molecule/LinkWithText";

const loginButton: any = getLoginButtonProps();

export const LoginPage: FunctionComponent<any> = ({props}) => {
    return (
        <form onSubmit={props.handleSubmit} className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_FORM_LOGIN])}>
            <h1>Login</h1>
            <div className={CSS_CLASS_CONTAINER}>
                <div className={getClassNameAttribute([CSS_CLASS_CONTAINER, CSS_CLASS_FORM_GROUP])}>
                    <FormForInput props={props.formForEmail}/>
                    <FormForInput props={props.formForPassword}/>
                </div>
                <div>
                    <CustomButton props={loginButton}/>
                </div>
            </div>
            <LinkWithText props={props.linkToRegistration}/>
        </form>
    );
};