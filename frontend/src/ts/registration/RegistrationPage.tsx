import {FunctionComponent} from "react";
import {
    getCreateAccountButtonProps,
    getLoginButtonLink
} from "./RegistrationFunctions";
import {CustomButton} from "../component/atom/button/CustomButton";
import {Error} from "../general-page/error/Error";
import {FormForInput} from "../component/molecule/form-attribute/FormForInput";

const createAccountButton: any = getCreateAccountButtonProps();

export const RegistrationPage: FunctionComponent<any> = ({backendError, handleSubmit, formForEmail, formForFirstName,
                                                             formForSurname, formForPassword,
                                                             formForPasswordConfirmation}) => {
    if (backendError) {
        return (
            <Error error={backendError}/>
        );
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Registration</h1>
                <FormForInput props={formForEmail}/>
                <FormForInput props={formForFirstName}/>
                <FormForInput props={formForSurname}/>
                <FormForInput props={formForPassword}/>
                <FormForInput props={formForPasswordConfirmation}/>
                <div>
                    <CustomButton props={createAccountButton}/>
                </div>
                <div>
                    <h2>Are you already registered?</h2>
                    {getLoginButtonLink()}
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
};
