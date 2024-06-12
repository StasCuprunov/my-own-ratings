import {FunctionComponent, useEffect} from "react";
import {setSiteTitle} from "../../utility/WebsiteUtility";
import {NotFoundPage} from "./NotFoundPage";

export const NotFound: FunctionComponent<any> = () => {

    useEffect(() => {
        setSiteTitle("not found");
    }, []);

    return (
        <NotFoundPage/>
    );
};