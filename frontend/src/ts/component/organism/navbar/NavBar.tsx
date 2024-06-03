import {FunctionComponent} from "react";
import {useAuth} from "../../../context/AuthContext";

import {getCreateRatingProps, getLogoutProps, getStartPageProps} from "./NavBarFunctions";
import {NavBarButtonLink} from "../../atom/button/link/NavBarButtonLink";

const logout: any = getLogoutProps();
const startPage: any = getStartPageProps();
const createRating: any = getCreateRatingProps();

export const NavBar: FunctionComponent<any> = () => {
    const {authenticated} = useAuth();

    if (!authenticated) {
        return <></>;
    }

    return (
        <div className={"navbar"}>
            <NavBarButtonLink props={startPage}/>
            <NavBarButtonLink props={createRating}/>
            <NavBarButtonLink props={logout}/>
        </div>
    );
};