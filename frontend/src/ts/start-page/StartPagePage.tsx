import {FunctionComponent} from "react";

import {CreateButtonLink} from "../component/atom/button/link/CreateButtonLink";
import {WEBSITE_ROUTING_RATINGS_CREATE} from "../constant/routing/WebsiteRoutingConstants";
import {getColumns} from "./StartPageFunctions";
import {DataGrid} from "@mui/x-data-grid";
import {customizePaginationDefault} from "../utility/DataGridUtility";
import {PAGINATION_SIZE_LIST} from "../constant/DataGridConstants";

const createButtonLink: any = {
    to: WEBSITE_ROUTING_RATINGS_CREATE
}

const columns: any = getColumns();

const initialState: any = customizePaginationDefault();

export const StartPagePage: FunctionComponent<any> = ({name, ratingDTOs}) => {

    return (
        <div>
            <h1>Hello {name}!</h1>
            <div>
                <h2>Create a new rating</h2>
                <CreateButtonLink props={createButtonLink}/>
            </div>
            <div>
                <h2>Your ratings</h2>
                <DataGrid autoHeight rows={ratingDTOs} columns={columns} initialState={initialState}
                          pageSizeOptions={PAGINATION_SIZE_LIST} disableRowSelectionOnClick
                />
            </div>
        </div>
    );
};