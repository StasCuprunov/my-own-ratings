import {useNavigate} from "react-router-dom";
import {PasswordRegex} from "./PasswordRegex";
import {ChangeEvent, FunctionComponent, useMemo, useState, useEffect} from "react";
import {User} from "../model/User";
import {
    createUser,
    getInputEmailProps,
    getInputFirstNameProps,
    getInputPasswordConfirmationProps,
    getInputPasswordProps,
    getInputSurnameProps
} from "./RegistrationFunctions";
import {WEBSITE_ROUTING_INDEX} from "../constant/routing/WebsiteRoutingConstants";
import {RegistrationPage} from "./RegistrationPage";

export const Registration: FunctionComponent<any> = ({props}) => {
    const navigate = useNavigate();
    const maxLengthString: number = props.maximumLengthOfString;

    const passwordRegex: PasswordRegex = useMemo(() => new PasswordRegex(props.atLeastOneDigitRegex,
        props.atLeastOneEnglishUpperCaseLetterRegex, props.atLeastOneEnglishLowerCaseLetterRegex,
        props.atLeastOneValidSpecialCharacterRegex, props.enumerationOfValidSpecialCharacters), [])

    const [user, setUser] = useState(new User());
    const [passwordConfirmation, setPasswordConfirmation] = useState("");

    const [isPasswordValid, setIsPasswordValid] = useState(true);
    const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] =
        useState(true);

    const [passwordErrorText, setPasswordErrorText] =
        useState<string>("");
    const [backendError, setBackendError] = useState(null);

    const handleUserChange = (field: string) => {
        return (e: ChangeEvent<HTMLInputElement>) => {
            setUser((prev: any) => ({
                ...prev,
                [field]: e.target.value
            }));
        };
    };

    const handlePasswordConfirmationChange = (event: any) => {
        setPasswordConfirmation(event.target.value);
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        if (!isPasswordValid || !isPasswordConfirmationValid) {
            return;
        }
        let result: any = await createUser(user);

        const {error} = result;
        if (error) {
            setBackendError(error);
        }
        else {
            navigate(WEBSITE_ROUTING_INDEX);
        }
    };

    useEffect(() => {
        checkPasswordConfirmation();
    }, [passwordConfirmation]);

    useEffect(() => {
        checkPassword();
    }, [user.password]);

    const checkPassword = () => {
        const { isPasswordValid, passwordErrorText } = passwordRegex.checkPassword(user.password);
        setIsPasswordValid(isPasswordValid);
        setPasswordErrorText(passwordErrorText);
    };

    const checkPasswordConfirmation = () => {
        let isValid: boolean = true;
        if (passwordConfirmation !== user.password) {
            isValid = false;
        }
        setIsPasswordConfirmationValid(isValid);
    };

    const inputEmail: any = useMemo(() =>
            getInputEmailProps(user.email, maxLengthString, handleUserChange("email")),
        [user.email]
    );
    let inputFirstName: any = useMemo( () =>
            getInputFirstNameProps(user.firstName, maxLengthString, handleUserChange("firstName")),
        [user.firstName]
    );
    let inputSurname: any = useMemo(() =>
            getInputSurnameProps(user.surname, maxLengthString, handleUserChange("surname")),
        [user.surname]
    );
    let inputPassword: any = useMemo(() =>
            getInputPasswordProps(user.password, props.passwordMinimumLength, props.passwordMaximumLength,
                handleUserChange("password")),
        [user.password]
    );
    let inputPasswordConfirmation: any = useMemo(() =>
            getInputPasswordConfirmationProps(passwordConfirmation, handlePasswordConfirmationChange),
        [passwordConfirmation]
    );

    return (
        <RegistrationPage backendError={backendError} handleSubmit={handleSubmit} inputEmail={inputEmail}
                          inputFirstName={inputFirstName} inputSurname={inputSurname} inputPassword={inputPassword}
                          isPasswordValid={isPasswordValid} passwordErrorText={passwordErrorText}
                          inputPasswordConfirmation={inputPasswordConfirmation}
                          isPasswordConfirmationValid={isPasswordConfirmationValid}
        />
    );
};