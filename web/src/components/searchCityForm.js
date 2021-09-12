import { Component } from "react";
import axios from "axios";
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css";
import Loader from "react-loader-spinner";

class searchCityForm extends Component {
  state = {
    cities: [],
    initialLetter: "",
    submit: false,
    alert: "",
    loader: false,
  };

  onClear = () => {
    this.setState({ initialLetter: "", cities: [], alert:""});
  };

  onSubmit = (e) => {
    e.preventDefault();
    if (this.state.initialLetter === "") {
      this.setState({ alert: "Enter some letter to Search city" });
    } else {
      this.searchCities(this.state.initialLetter);
    }
    console.log(this.state.initialLetter);
  };
  onChange = async (e) => {
    this.setState({ initialLetter: e.target.value, loader: true });
    if (e.target.value !== "") {
      this.searchCities(e.target.value);
    }
  };
  searchCities = async (initialLetters) => {
    this.setState({ loader: true });
    const res = await axios.get(
      `http://localhost:8080/city?letter=${initialLetters}`
    );
    if (res.data.cod === "200") {
      this.setState({
        cities: res.data.list,
        loader: false,
      });
    } else {
      this.setState({ alert: res.data.message, loader: false });
    }
    console.log(res.data);
  };
  render() {
      const {initialLetter, alert, loader, cities} = this.state;
    return (
      <div className="searchComponent">
        <form className="form" onSubmit={this.onSubmit}>
          <input
            type="text"
            name="text"
            value={initialLetter}
            onChange={this.onChange}
            placeholder="Search"
          />
          <input
            type="submit"
            name="submit"
            className="btn btn-dark btn-block"
          />
          {initialLetter !== "" && (
            <input
              type="button"
              name="clear"
              onClick={this.onClear}
              value="Clear"
              className="btn btn-dark btn-block"
            />
          )}
        </form>
        {loader && initialLetter !== "" && (
          <Loader type="Puff" color="#00BFFF" height={100} width={100} />
        )}
        { !loader &&
          initialLetter !== "" &&
          cities !== [] &&
          cities !== null && (
            <h1>
              Number of cities starting with {this.state.initialLetter} is {this.state.cities.length}
              {cities.map((city) => (
                <div key={city.id}>* {city.name}</div>
              ))}
            </h1>
          )}
        {alert && initialLetter === "" && (
          <div className="AlertBox">
            <h1>{alert}</h1>
          </div>
        )}
      </div>
    );
  }
}

export default searchCityForm;
