import {FunctionComponent} from "react";
import {getDescription} from "./ErrorFunctions";
import {ErrorPage} from "./ErrorPage";

export const Error: FunctionComponent<any> = ({error}) => {
    console.log(error);

    const message: string = error.message;
    const description: string = getDescription(error);

    return (
        <ErrorPage message={message} description={description}/>
    );
};