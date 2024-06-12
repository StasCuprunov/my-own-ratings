import {FunctionComponent} from "react";
import CancelIcon from '@mui/icons-material/Cancel';
import {CSS_CLASS_CANCEL_BUTTON} from "../../../../constant/CSSClassNameConstants";
import {ButtonLinkWithIcon} from "./ButtonLinkWithIcon";

export const CancelButtonLink: FunctionComponent<any> = ({to}) => {
    const buttonLinkProps: any = {
        to: to,
        text: "Cancel",
        icon: CancelIcon,
        className: CSS_CLASS_CANCEL_BUTTON
    }
    return (
        <ButtonLinkWithIcon props={buttonLinkProps}/>
    );
};