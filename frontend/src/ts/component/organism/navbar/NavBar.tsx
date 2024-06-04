import {FunctionComponent} from "react";
import {useAuth} from "../../../context/AuthContext";

import {getCreateRatingProps, getLogoutProps, getStartPageProps} from "./NavBarFunctions";
import {NavBarButtonLink} from "../../atom/button/link/NavBarButtonLink";
import {CSS_CLASS_NAV_BAR} from "../../../constant/CSSClassNameConstants";

const logout: any = getLogoutProps();
const startPage: any = getStartPageProps();
const createRating: any = getCreateRatingProps();

export const NavBar: FunctionComponent<any> = () => {
    const {authenticated} = useAuth();

    if (!authenticated) {
        return <></>;
    }

    return (
        <div className={CSS_CLASS_NAV_BAR}>
            <NavBarButtonLink props={startPage}/>
            <NavBarButtonLink props={createRating}/>
            <NavBarButtonLink props={logout}/>
        </div>
    );
};