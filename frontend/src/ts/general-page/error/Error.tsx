import {FunctionComponent} from "react";
import {getDescription, getReloadButtonProps} from "./ErrorFunctions";
import {ErrorPage} from "./ErrorPage";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_REFRESH} from "../../constant/routing/WebsiteRoutingConstants";

export const Error: FunctionComponent<any> = ({error}) => {
    console.log(error);

    const navigate = useNavigate();

    const handleReloadOnClick = () => {
        navigate(WEBSITE_ROUTING_REFRESH);
    };

    const reloadButtonProps: any = getReloadButtonProps(handleReloadOnClick);

    const message: string = error.message;
    const description: string = getDescription(error);

    return (
        <ErrorPage message={message} description={description} reloadButtonProps={reloadButtonProps}/>
    );
};