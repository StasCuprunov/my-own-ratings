import {FunctionComponent, useMemo} from "react";
import {StartPagePage} from "./StartPagePage";
import {getGreetingName} from "./StartPageFunctions";
import {PageTemplate} from "../component/PageTemplate";

export const StartPage: FunctionComponent<any> = ({props}) => {
    const userDTO: any = props.userDTO;

    const name: string = useMemo(() => getGreetingName(userDTO.firstName, userDTO.surname), []);

    const pageProps: any = {
        name: name,
        ratingDTOs: props.ratingDTOs
    };

    return (
        <PageTemplate Component={StartPagePage} props={pageProps}/>
    );
};