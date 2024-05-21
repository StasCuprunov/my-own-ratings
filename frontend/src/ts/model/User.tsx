import {setIdAttribute, setStringAttribute} from "../utility/ClassUtility";

export class User {
    id: string | null;
    email: string;
    firstName: string;
    surname: string;
    password: string;

    constructor();
    constructor(id?: string, email?: string, firstName?: string, surname?: string, password?: string) {
        this.id = setIdAttribute(id);
        this.email = setStringAttribute(email);
        this.firstName = setStringAttribute(firstName);
        this.surname = setStringAttribute(surname);
        this.password = setStringAttribute(password);
    }
}