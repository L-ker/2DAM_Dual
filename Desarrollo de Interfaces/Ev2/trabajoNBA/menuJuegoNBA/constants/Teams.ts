// constants/teams.ts

export interface Team {
  id: string;
  name: string;
  logo: any;       // 'any' porque require() devuelve un módulo de imagen
  players: string[];
}

const teams: Team[] = [
  {
    id: 'lakers',
    name: 'Los Angeles Lakers',
    logo: require('../assets/lakers.png'),
    players: ['LeBron James', 'Anthony Davis', 'Russell Westbrook', 'Carmelo Anthony', 'D’Angelo Russell']
  },
  {
    id: 'pistons',
    name: 'Detroit Pistons',
    logo: require('../assets/pistons.png'),
    players: ['Chauncey Billups', 'Ben Wallace', 'Richard Hamilton', 'Isaiah Thomas', 'Joe Dumars']
  },
  {
    id: 'bulls',
    name: 'Chicago Bulls',
    logo: require('../assets/bulls.png'),
    players: ['Michael Jordan', 'Scottie Pippen', 'Dennis Rodman', 'Derrick Rose', 'Zach LaVine']
  },
  {
    id: 'celtics',
    name: 'Boston Celtics',
    logo: require('../assets/celtics.png'),
    players: ['Jayson Tatum', 'Jaylen Brown', 'Marcus Smart', 'Paul Pierce', 'Kevin Garnett']
  },
  {
    id: 'knicks',
    name: 'New York Knicks',
    logo: require('../assets/knicks.png'),
    players: ['Patrick Ewing', 'Walt Frazier', 'Allan Houston', 'Carmelo Anthony', 'Julius Randle']
  },
  {
    id: 'east-allstars',
    name: 'East All-Stars',
    logo: require('../assets/east-allstars.png'),
    players: ['Giannis Antetokounmpo', 'Joel Embiid', 'Kevin Durant', 'Kyrie Irving', 'Jayson Tatum']
  },
  {
    id: 'west-allstars',
    name: 'West All-Stars',
    logo: require('../assets/west-allstars.png'),
    players: ['LeBron James', 'Stephen Curry', 'Nikola Jokic', 'Luka Doncic', 'Anthony Davis']
  },
  {
    id: 'jazz',
    name: 'Utah Jazz',
    logo: require('../assets/jazz.png'),
    players: ['John Stockton', 'Karl Malone', 'Deron Williams', 'Mike Conley', 'Donovan Mitchell']
  },
  {
    id: 'supersonics',
    name: 'Seattle Supersonics',
    logo: require('../assets/supersonics.png'),
    players: ['Gary Payton', 'Shawn Kemp', 'Ray Allen', 'Detlef Schrempf', 'Fred Brown']
  },
  {
    id: 'suns',
    name: 'Phoenix Suns',
    logo: require('../assets/suns.png'),
    players: ['Steve Nash', 'Charles Barkley', 'Devin Booker', 'Kevin Johnson', 'Amar’e Stoudemire']
  }
];

export default teams;