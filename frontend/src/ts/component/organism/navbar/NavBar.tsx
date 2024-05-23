import {FunctionComponent} from "react";
import {useAuth} from "../../../context/AuthContext";
import {ButtonLink} from "../../atom/button/ButtonLink";
import {getLogoutObject} from "./NavBarFunctions";

const logout: any = getLogoutObject();

export const NavBar: FunctionComponent<any> = () => {
    const {authenticated} = useAuth();
    return (
        <div>
            {authenticated &&
                <ButtonLink props={logout}/>
            }
        </div>
    );
};