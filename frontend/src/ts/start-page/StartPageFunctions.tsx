import {GridColDef } from '@mui/x-data-grid';

import {useGet} from "../interface/useGet";
import {API_ROUTING_START_PAGE} from "../constant/routing/APIRoutingConstants";
import {getWebsiteRoutingRatingsById} from "../constant/routing/WebsiteRoutingConstants";
import {ButtonLink} from "../component/atom/button/link/ButtonLink";

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
            field: 'name',
            headerName: 'Name',
            width: 400
        },
        {
            field: 'description',
            headerName: 'Description',
            width: 400
        },
        {
            field: 'id',
            headerName: 'Link',
            sortable: false,
            filterable: false,
            renderCell: buttonLink
        }
    ];
};

const buttonLink = (params: any) => {
    return (
        <ButtonLink props={getButtonLinkToRatingObject(params.row.id)}/>
    );
};

const getButtonLinkToRatingObject = (id: string) => {
    return {
        type: "button",
        text: "Go",
        to: getWebsiteRoutingRatingsById(id)
    };
};