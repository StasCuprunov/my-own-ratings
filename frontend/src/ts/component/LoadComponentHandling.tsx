import React, {FunctionComponent, useEffect} from "react";
import {Error} from "../general-page/error/Error";
import {setSiteTitle} from "../utility/WebsiteUtility";
import {useAuth} from "../context/AuthContext";
import {NotAuthorizedPage} from "../general-page/NotAuthorizedPage";

export const LoadComponentHandling: FunctionComponent<any> = ({Component, props, error, documentTitle,
                                                                  needsAuthentication}) =>  {
    const auth = useAuth();

    useEffect(() => {
        setSiteTitle(documentTitle);
    }, [documentTitle]);

    if (needsAuthentication && !auth.authenticated) {
        return (
            <NotAuthorizedPage/>
        );
    }

    if (error) {
        return (
            <Error error={error}/>
        );
    }
    else if (props) {
        return (
            <Component props={props}/>
        );
    }
    return (
        <></>
    );
}