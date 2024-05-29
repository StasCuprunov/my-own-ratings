import {FunctionComponent} from "react";
import {ButtonLink} from "./ButtonLink";

export const CancelButtonLink: FunctionComponent<any> = ({to}) => {
    const buttonLinkProps: any = {
        to: to,
        type: "button",
        text: "Cancel"
    }
    return (
        <ButtonLink props={buttonLinkProps}/>
    );
};