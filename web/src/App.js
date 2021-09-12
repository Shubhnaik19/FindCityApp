import './styles/_App.scss';
import {Component} from 'react';
import SearchComponent from './components/searchCityForm';

class App extends Component {
  render(){
  return (
    <div className="App">
      <h1>Search the city with initial letter:</h1>
  <SearchComponent></SearchComponent>
    </div>
  );
  }
}

export default App;
