import {ChangeEvent, FunctionComponent, useMemo, useState} from "react";
import {Label} from "../component/atom/Label";
import {Input} from "../component/atom/Input";
import {
    getInputEmailObject,
    getInputFirstNameObject,
    getInputPasswordConfirmation,
    getInputPasswordObject,
    getInputSurnameObject,
    getLabelEmailObject,
    getLabelFirstNameObject,
    getLabelPasswordConfirmation,
    getLabelPasswordObject,
    getLabelSurnameObject
} from "./RegistrationFunctions";
import {PasswordRegex} from "./PasswordRegex";

export const RegistrationPage: FunctionComponent<any> = ({props}) => {
    const maxLengthString: number = props.maximumLengthOfString;

    const labelEmail: any = useMemo(() => getLabelEmailObject(), []);
    const labelFirstName: any = useMemo(() => getLabelFirstNameObject(), []);
    const labelSurname: any = useMemo(() => getLabelSurnameObject(), []);
    const labelPassword: any = useMemo(() => getLabelPasswordObject(), []);
    const labelPasswordConfirmation: any = useMemo(() => getLabelPasswordConfirmation(), []);
    const passwordRegex: PasswordRegex = new PasswordRegex(props.atLeastOneDigitRegex,
        props.atLeastOneEnglishUpperCaseLetterRegex, props.atLeastOneEnglishLowerCaseLetterRegex,
        props.atLeastOneValidSpecialCharacterRegex, props.enumerationOfValidSpecialCharacters);

    const [user, setUser] = useState({
        id: "",
        email: "",
        firstName: "",
        surname: "",
        password: ""
    });
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrors, setPasswordErrors] = useState<string[]>([]);

    const handlePasswordConfirmationChange = (event: any) => {
        setPasswordConfirmation(event.target.value);
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();
        console.log(user)
        setIsPasswordValid(true);
        setIsPasswordConfirmationValid(true);

        passwordRegex.checkPassword(user.password, setIsPasswordValid, setPasswordErrors);
        if (passwordConfirmation !== user.password) {
            setIsPasswordConfirmationValid(false);
        }
        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
    };

    const handleUserChange = (field: string, setUser: any) => {
        return (e: ChangeEvent<HTMLInputElement>) => {
            setUser((prev: any) => ({
                ...prev,
                [field]: e.target.value
            }));
        };
    };

    const inputEmail: any = useMemo(() =>
            getInputEmailObject(user.email, maxLengthString, handleUserChange("email", setUser)),
        [user.email]
    );
    let inputFirstName: any = useMemo( () =>
        getInputFirstNameObject(user.firstName, maxLengthString, handleUserChange("firstName", setUser)),
        [user.firstName]
    );
    let inputSurname: any = useMemo(() =>
        getInputSurnameObject(user.surname, maxLengthString, handleUserChange("surname", setUser)),
        [user.surname]
    );
    let inputPassword: any = useMemo(() =>
        getInputPasswordObject(user.password, props.passwordMinimumLength, props.passwordMaximumLength,
            handleUserChange("password", setUser)),
        [user.password]
    );
    let inputPasswordConfirmation: any = useMemo(() =>
        getInputPasswordConfirmation(passwordConfirmation, handlePasswordConfirmationChange),
        [passwordConfirmation]
    );

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Registration</h1>
                <div>
                    <Label props={labelEmail}/>
                    <Input props={inputEmail}/>
                </div>
                <div>
                    <Label props={labelFirstName}/>
                    <Input props={inputFirstName}/>
                </div>
                <div>
                    <Label props={labelSurname}/>
                    <Input props={inputSurname}/>
                </div>
                <div>
                    <Label props={labelPassword}/>
                    <Input props={inputPassword}/>
                    {!isPasswordValid &&
                        <span>{passwordErrors.toString()}</span>
                    }
                </div>
                <div>
                    <Label props={labelPasswordConfirmation}/>
                    <Input props={inputPasswordConfirmation}/>
                    {!isPasswordConfirmationValid &&
                        <span>The confirmation must be equal to the password.</span>
                    }
                </div>
                <div>
                    <button type="submit">Create Account</button>
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                        <li>
                            <sup>2</sup>
                            The password must contain minimum one digit, one uppercase English letter, one lowercase
                            English letter, and one of the following special
                            characters: {props.enumerationOfValidSpecialCharacters}
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
}
