import {FunctionComponent} from "react";
import {DataGrid} from "@mui/x-data-grid";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import {WEBSITE_ROUTING_RATINGS_CREATE} from "../constant/routing/WebsiteRoutingConstants";
import {getColumns} from "./StartPageFunctions";
import {customizePaginationDefault} from "../utility/DataGridUtility";
import {PAGINATION_SIZE_LIST} from "../constant/DataGridConstants";
import {CSS_CLASS_CONTAINER, CSS_CLASS_WEBSITE_CONTAINER} from "../constant/CSSClassNameConstants";
import {ButtonLinkWithIcon} from "../component/atom/button/link/ButtonLinkWithIcon";

const createButtonLink: any = {
    to: WEBSITE_ROUTING_RATINGS_CREATE,
    text: "Create rating",
    icon: AddCircleOutlineIcon
};

const columns: any = getColumns();

const initialState: any = customizePaginationDefault();

export const StartPagePage: FunctionComponent<any> = ({props}) => {

    return (
        <>
            <h1>Hello {props.name}!</h1>
            <div className={CSS_CLASS_CONTAINER}>
                <ButtonLinkWithIcon props={createButtonLink}/>
            </div>
            <div className={CSS_CLASS_CONTAINER}>
                <h2>Your ratings</h2>
                <DataGrid autoHeight rows={props.ratingDTOs} columns={columns} initialState={initialState}
                          pageSizeOptions={PAGINATION_SIZE_LIST} disableRowSelectionOnClick
                />
            </div>
        </>
    );
};
