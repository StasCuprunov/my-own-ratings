import {setIdAttribute,setStringAttribute} from "../utility/ClassUtility";

export class Rating {
    id: string | null;
    userId: string;
    name: string;
    description: string;

    constructor(id?: string, userId?: string, name?: string, description?: string) {
        this.id = setIdAttribute(id);
        this.userId = setStringAttribute(userId);
        this.name = setStringAttribute(name);
        this.description = setStringAttribute(description);
    }
}