import {useEffect, useState} from 'react'
import './App.css'
import axios from "axios";
function App() {
    const [programmers,setProgrammers]=useState<[{problem:{index:string,rating:number,contestId:number,name:string}}]>();
    const loadRankList = () => {
        const url="http://localhost:8080/get-rating-list";
        axios.get(url).then(res=>{
            setProgrammers(res.data);
        })
    }
    useEffect(()=>{
        loadRankList();
    },[]);
    const mark={};
    const [frequency,setFrequency]=useState({});
    useEffect(()=>{
        if(!programmers) return;
        const frequency={};
        programmers.forEach(submission=>{
            const id=submission.problem.contestId+""+submission.problem.index;
            frequency[id]=(frequency[id]??0)+1;
        });
        programmers.sort((a,b)=>{
            const id1=a.problem.contestId+""+a.problem.index;
            const id2=b.problem.contestId+""+b.problem.index;
            return -frequency[id1]+frequency[id2];
        });
        setFrequency(frequency);
        setProgrammers(programmers);
    },[programmers])
    let idx=0;
  return (
    <>
        <table>
            <tr>
                <th>SL</th>
                <th>Problem</th>
                <th>Contest ID</th>
                <th>Problem Name</th>
                <th>Rating</th>
                <th>Common Count</th>
                <th>Problem Link</th>
            </tr>
            {programmers&&programmers.map((submission)=>{
                const id=submission.problem.contestId+""+submission.problem.index;
                if(mark[id]) {
                    return null;
                }
                mark[id]=true;
                idx++;
                return (
                    <tr>
                        <td>{idx}</td>
                        <td>{submission.problem.index}</td>
                        <td>{submission.problem.contestId}</td>
                        <td>{submission.problem.name}</td>
                        <td>{submission.problem.rating}</td>
                        <td>{frequency[id]??0}</td>
                        <td className={'text-left'}><a href={"//codeforces.com/contest/"+submission.problem.contestId+"/problem/"+submission.problem.index}>{"https://codeforces.com/contest/"+submission.problem.contestId+"/problem/"+submission.problem.index}</a></td>
                    </tr>
                )
            })}
        </table>
    </>
  )
}

export default App
