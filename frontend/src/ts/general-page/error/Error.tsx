import {FunctionComponent} from "react";
import {getDescription, getReloadButtonProps} from "./ErrorFunctions";
import {ErrorPage} from "./ErrorPage";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_REFRESH} from "../../constant/routing/WebsiteRoutingConstants";
import {PageTemplate} from "../../component/PageTemplate";

export const Error: FunctionComponent<any> = ({error}) => {
    console.log(error);

    const navigate = useNavigate();

    const handleReloadOnClick = () => {
        navigate(WEBSITE_ROUTING_REFRESH);
    };

    const pageProps: any = {
        message: error.message,
        description: getDescription(error),
        reloadButtonProps: getReloadButtonProps(handleReloadOnClick)
    };

    return (
        <PageTemplate Component={ErrorPage} props={pageProps}/>
    );
};