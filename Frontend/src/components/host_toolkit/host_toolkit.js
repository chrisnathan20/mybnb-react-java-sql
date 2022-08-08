import React , { useEffect, useState } from "react";
import './host_toolkit.css'

function HostToolkit() {
    const[topAmenities, setTopAmenities] = useState([]);
    const[lowAmenities, setLowAmenities] = useState([]);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch('/mybnb/gettopamenities', {
            method: 'GET',
            headers: {"Content-Type": "application/json"},
            credentials: "include",}).then(response =>
          response.json().then(data => {
            setTopAmenities(data);
          })
        );
    }, []);

    useEffect(() => {
        // this is where we should send the fetch request, sample code below
        fetch('/mybnb/getlowestamenities', {
            method: 'GET',
            headers: {"Content-Type": "application/json"},
            credentials: "include",}).then(response =>
          response.json().then(data => {
            setLowAmenities(data);
          })
        );
    }, []);
    return (
        <>
        <div className="result_toolkit">
            <div className="toolkit_headers">TOP 3 AMENITIES</div>
            <div>
                {topAmenities.map((amenity) => (
                    <div><span>{amenity.name}: </span>{amenity.count}</div>
                ))}
            </div>
        </div>

        <div className="result_toolkit">
            <div className="toolkit_headers">LOWEST 3 AMENITIES</div>
            <div>
                {lowAmenities.map((amenity) => (
                    <div><span>{amenity.name}: </span>{amenity.count}</div>
                ))}
            </div>
        </div>
        </>
    )
}

export default HostToolkit