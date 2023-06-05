:- discontiguous
        pai/2,
        mae/2,
        casa/2,
        casado/2,
    	casasRelacionadas/2.

genitor(X,Y) :- 
    pai(X,Y),
    mae(X,Y).

irmao(X,Y) :- 
    (mae(Z, X), mae(Z, Y), X \= Y;
    pai(Z, X), pai(Z, Y), X \= Y).

descendente(X,Y) :- pai(X, Y).
descendente(X, Y) :- pai(X, Z), descendente(Z, Y).

casados(X,Y) :-
    (casado(X, Y);
    (pai(X, Z), mae(Y, Z));
    (mae(X, Z), pai(Y, Z))).

casasRelacionadas(X,Y) :-
    casado(X, Z), casado(Y, Z), X \= Y.

local(X,Z) :-
    casa(X, Y), mora(Y, Z).


casa(doran,martell).
casa(elia,martell).
casa(oberyn,martell).
casa(tyeneSand,martell).
casa(trystane,martell).

casa(olenna,tyrell).
casa(loras,tyrell).
casa(luthor,tyrell).
casa(mace,tyrell).
casa(margaery,tyrell).

casa(cersei,lannister).
casa(jaime,lannister).
casa(joanna,lannister).
casa(tyrion,lannister).
casa(tywin,lannister).

casa(joffrey,baratheon).
casa(myrcella,baratheon).
casa(ormund,baratheon).
casa(robert,baratheon).
casa(renly,baratheon).
casa(steffon,baratheon).
casa(stannis,baratheon).
casa(shireen,baratheon).
casa(tommen,baratheon).

casa(jon,arryn).
casa(robin,arryn).

casa(catelyn,tully).
casa(edmure,tully).
casa(hoster,tully).
casa(lysa,tully).

casa(arya,stark).
casa(benjen,stark).
casa(brandon,stark).
casa(brandonI,stark).
casa(eddard,stark).
casa(rickard,stark).
casa(lyanna,stark).
casa(rickon,stark).
casa(sansa,stark).
casa(robb,stark).

casa(aegonI,targaryen).
casa(aemon,targaryen).
casa(aegonV,targaryen).
casa(aegon,targaryen).
casa(aerysII,targaryen).
casa(daenerys,targaryen).
casa(jaehaerysII,targaryen).
casa(jonSnow,targaryen).
casa(maekarI,targaryen).
casa(rhaelle,targaryen).
casa(rhaegar,targaryen).
casa(raenys,targaryen).
casa(viserys,targaryen).

casa(ramsay,bolton).
casa(roose,bolton).

casa(balon,greyjoy).
casa(theon,greyjoy).

casa(roslin,frey).
casa(walder,frey).

casa(petyr,baelish).

casa(arryn, arryn).
casa(baratheon,baratheon).
casa(lannister,lannister).
casa(martell, martell).
casa(stark,stark).
casa(targaryen,targaryen).
casa(tully,tully).
casa(tyrell,tyrell).
mora(arryn,vale).
mora(baratheon,stormlands).
mora(lannister,westerland).
mora(martell,dorne).
mora(stark,north).
mora(targaryen,valyria).
mora(tully,riverlands).
mora(tyrell,reach).


pai(aegonI,maekarI). % Ajuste de descendência
pai(maekarI,aemon).
pai(maekarI,aegonV).
pai(aegonV,jaehaerysII).
pai(aegonV,rhaelle).
pai(jaehaerysII,aerysII).
pai(aerysII,rhaegar).
pai(aerysII,viserys).
pai(aerysII,daenerys).
pai(rhaegar,jonSnow).
pai(rhaegar,raenys).
pai(rhaegar,aegon).
mae(rhaelle,steffon).

pai(rickard,brandonI).
pai(rickard,eddard).
pai(rickard,bejen).
pai(rickard,lyanna).
pai(eddard,arya).
pai(eddard,brandon).
pai(eddard,rickon).
pai(eddard,sansa).
pai(eddard,robb).
pai(eddard,jonSnow).
mae(lyanna,jonSnow).

pai(hoster,catelyn).
pai(hoster,edmure).
pai(hoster,lysa).
mae(catelyn,arya).
mae(catelyn,brandon).
mae(catelyn,rickon).
mae(catelyn,sansa).
mae(catelyn,robb).
mae(lysa,robin).

pai(walder,roslin).

pai(jon,robin).

pai(ormund,steffon).
pai(steffon,robert).
pai(steffon,stannis).
pai(steffon,renly).
pai(stannis,shirren).
pai(robert,joffrey).
pai(robert,myrcella).
pai(robert,tommen).

pai(tywin,jaime).
pai(tywin,cersei).
pai(tywin,tyrion).
pai(jaime,joffrey).
pai(jaime,myrcella).
pai(jaime,tommen).
mae(joanna,jaime).
mae(joanna,cersei).
mae(joanna,tyrion).
mae(cersei,joffrey).
mae(cersei,myrcella).
mae(cersei,tommen).

pai(luthor,mace).
pai(mace,margaery).
pai(mace,loras).
mae(olenna,mace).

pai(desconhecido,doran).
pai(desconhecido,oberyn).
pai(desconhecido,elia).
pai(oberyn,tyeneSand).
pai(doran,trystane).
mae(elia,raenys).
mae(elia,aegon).


casado(rhaegar,lyanna).
casado(rhaegar,elia).
casado(rhaelle,ormund).

casado(eddard,catelyn).
casado(ramsay,sansa).
casado(sansa,ramsay).
casado(sansa,tyrion).
casado(tyrion,sansa).
casado(robb,talissa).
casado(talissa,robb).
casado(lyanna,rhaegar).

casado(catelyn,eddard).
casado(lysa,jon).
casado(lysa,petyr).
casado(petyr,lysa).
casa(petyr,baelish).
casado(edmure,roslin).
casado(roslin,edmure).

casado(jon,lysa).

casado(ormund,rhaelle).
casado(renly,margaery).
casado(joffrey,margaery).
casado(tommen,margaery).
casado(robert,cersei).

casado(cersei,robert).
casado(tywin,joanna).
casado(joanna,tywin).
casado(tyrion,sansa).

casado(margaery,joffrey).
casado(margaery,tommen).
casado(margaery,renly).
casado(oleanna,luthor).
casado(luthor,oleanna).

casado(elia,rhaegar).


/** <examples>
?- pai(eddard, arya).
?- pai(robb, jonSnow).
?- irmao(renly, jonSnow).
?- irmao(sansa, X).
?- descendente(jonSnow, rickard).
?- descendente(rickard,X).
?- casados(rhaegar, lyanna).
?- casados(cersei,X). (a pesquisa retorna o nome do pai para cada filho gerado)
?- casasRelacionadas(tully,X).
?- casasRelacionadas(baratheon,targaryen).
?- local(targaryen, valyria).
?- local(stark, X).
*/