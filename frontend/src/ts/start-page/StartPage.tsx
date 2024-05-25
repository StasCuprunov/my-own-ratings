import {FunctionComponent, useMemo} from "react";
import {StartPagePage} from "./StartPagePage";
import {getGreetingName} from "./StartPageFunctions";

export const StartPage: FunctionComponent<any> = ({props}) => {
    const userDTO: any = props.userDTO;

    const name: string = useMemo(() => getGreetingName(userDTO.firstName, userDTO.surname), []);

    return (
        <StartPagePage name={name} ratingDTOs={props.ratingDTOs}/>
    );
};