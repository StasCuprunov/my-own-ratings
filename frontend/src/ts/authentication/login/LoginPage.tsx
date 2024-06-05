import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {ButtonLink} from "../../component/atom/button/link/ButtonLink";
import {
    getLoginButtonProps,
    registrationButtonLinkProps
} from "./LoginFunctions";
import {FormForInput} from "../../component/molecule/form-attribute/FormForInput";

const loginButton: any = getLoginButtonProps();

export const LoginPage: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            <h1>Login</h1>
            <div>
                <form onSubmit={props.handleSubmit}>
                    <FormForInput props={props.formForEmail}/>
                    <FormForInput props={props.formForPassword}/>
                    <div>
                        <CustomButton props={loginButton}/>
                    </div>
                </form>
            </div>
            <div>
                <h2>Don't you have an account?</h2>
                <ButtonLink props={registrationButtonLinkProps()}/>
            </div>
        </div>
    );
};