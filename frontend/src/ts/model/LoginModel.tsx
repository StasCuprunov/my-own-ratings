import {setStringAttribute} from "../utility/ClassUtility";

export class LoginModel {
    email: string;
    password: string;

    constructor();
    constructor(email?: string, password?: string) {
        this.email = setStringAttribute(email);
        this.password = setStringAttribute(password);
    }
}