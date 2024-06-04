import {GridColDef } from '@mui/x-data-grid';
import EastIcon from '@mui/icons-material/East';

import {useGet} from "../interface/useGet";
import {API_ROUTING_START_PAGE} from "../constant/routing/APIRoutingConstants";
import {getWebsiteRoutingRatingsById} from "../constant/routing/WebsiteRoutingConstants";
import {LinkWithIcon} from "../component/atom/LinkWithIcon";

export const useStartPage = () => {
    return useGet(API_ROUTING_START_PAGE);
};

export const getGreetingName = (firstName: string | null, surname: string | null): string =>{
    let name: string = "User";

    if (firstName && surname) {
        name = firstName + " " + surname;
    }
    else if (firstName) {
        name = firstName;
    }
    else if (surname) {
        name = surname
    }
    return name;
};

export const getColumns = (): GridColDef[] => {
    return [
        {
            field: "name",
            headerName: "Name",
            flex: 1
        },
        {
            field: "description",
            headerName: "Description",
            flex: 1
        },
        {
            field: "id",
            headerName: "Link",
            sortable: false,
            filterable: false,
            renderCell: link,
            flex: 1
        }
    ];
};

const link = (params: any) => {
    const props: any = {
        to: getWebsiteRoutingRatingsById(params.row.id),
        text: "Go to rating",
        icon: EastIcon
    };
    return (
        <LinkWithIcon props={props}/>
    );
};