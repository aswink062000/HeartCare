import React, {useEffect, useState, useLayoutEffect, cloneElement} from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import {Chart} from "react-google-charts";

function Grafico(props) {
    const [dataGrafico,setDataGrafico] = useState([]);
    const [isChange,setIsChange] = useState(false);

    const fromJsonToArray = (categorieNonJsonate) => {
        let arr = JSON.stringify(categorieNonJsonate);
        arr = arr.replace("[","")
        arr = arr.replace("]","")
        arr = arr.replaceAll("\"","")
        arr = arr.split(",")
        return arr;
    }

    let arrayCategorie = fromJsonToArray(props.categorie);
    let categoria = arrayCategorie[0];
    const [categoriaSelezionata,setCategoriaSelezionata] = useState(categoria);
    let attributiFiltrati = [];
    let filtrate = [];

    const filterByCategoria = (categoria) => {
        filtrate = [];
        attributiFiltrati = [];
            Object.keys(props.misurazioni).map(index =>{
                if(props.misurazioni[index]["categoria"] === categoria){
                    filtrate.push(Object.values(props.misurazioni[index]["misurazione"]));
                    attributiFiltrati = Object.keys(props.misurazioni[index]["misurazione"])
                }
            })
            return [filtrate,attributiFiltrati];
    }

    useEffect(() => {
        const [filtrate,attributiFiltrati] = filterByCategoria(categoriaSelezionata);
        setDataGrafico(filtrate);
    }, [categoriaSelezionata]);

    return (
        <div>
            <Chart
                chartType="BarChart"
                data={dataGrafico}
                options={{
                    title: 'Grafico',
                    hAxis: {title: 'X', titleTextStyle: {color: '#333'}},
                    vAxis: {title: 'Y', titleTextStyle: {color: '#333'}},
                }}
                width={'100%'}
                height={'400px'}
                legendToggle
            />
        </div>
    );
}