import {FunctionComponent} from "react";
import {CustomButton} from "../../component/atom/button/CustomButton";
import {ButtonLink} from "../../component/atom/button/link/ButtonLink";
import {
    getLoginButtonProps,
    registrationButtonLinkProps
} from "./LoginFunctions";
import {Error} from "../../general-page/error/Error";
import {FormForInput} from "../../component/molecule/form-attribute/FormForInput";

const loginButton: any = getLoginButtonProps();

export const LoginPage: FunctionComponent<any> = ({backendError, handleSubmit, formForEmail, formForPassword}) => {

    if (backendError) {
        return (
            <Error error={backendError}/>
        );
    }

    return (
        <div>
            <h1>Login</h1>
            <div>
                <form onSubmit={handleSubmit}>
                    <FormForInput props={formForEmail}/>
                    <FormForInput props={formForPassword}/>
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