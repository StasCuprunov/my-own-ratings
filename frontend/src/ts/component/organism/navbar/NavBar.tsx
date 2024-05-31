import {FunctionComponent} from "react";
import {useAuth} from "../../../context/AuthContext";
import {ButtonLink} from "../../atom/button/link/ButtonLink";
import {getCreateRatingProps, getLogoutProps, getStartPageProps} from "./NavBarFunctions";

const logout: any = getLogoutProps();
const startPage: any = getStartPageProps();
const createRating: any = getCreateRatingProps();

export const NavBar: FunctionComponent<any> = () => {
    const {authenticated} = useAuth();
    return (
        <div>
            {authenticated &&
                <div>
                    <ButtonLink props={startPage}/>
                    <ButtonLink props={createRating}/>
                    <ButtonLink props={logout}/>
                </div>
            }
        </div>
    );
};