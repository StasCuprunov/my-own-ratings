import React, {FunctionComponent} from "react";
import {ErrorPage} from "../ErrorPage";

export const ComponentHandling: FunctionComponent<any> = ({Component, error}) =>  {
    return (
        error ? <ErrorPage error={error}/> : <Component/>
    );
}