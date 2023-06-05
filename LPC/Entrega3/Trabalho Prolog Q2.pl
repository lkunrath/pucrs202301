%vazio(X): Retorna verdadeiro caso X seja uma lista vazia.
vazio([]).

%pertence(X,Y): Retorna verdadeiro caso o elemento Y pertença a lista X.
pertence(X, [X|_]).
pertence(X, [_|T]) :- pertence(X,T).
%Forma de pesquisa. pertence(elementoconsultado,[elemento1,elemento2]).

%somatorio(X,Y): Retorna verdadeiro caso X seja uma lista de números e Y seja o somatório dessa lista
somatorio([], 0).
somatorio([H|T], Som) :- somatorio(T, Som1), Som is H + Som1.
%Forma de pesquisa. somatorio([elemento1,elemento2],ResultadoSoma).

%indice(X,Y,I): Retorna verdadeiro caso o elemento Y pertença a lista X e I seja a posição do elemento I na lista
indice(E, [E|_], 0):- !.
indice(E, [_|T], I):- indice(E, T, X), I is X + 1.
%Forma de pesquisa. indice(elementoconsultado,[elemento1,elemento2],indiceconsultado).

%reverso(X,Y): Retorna verdadeiro caso X e Y sejam listas e Y tenha os elementos em ordem inversa de X. Exemplo X = [0, 1, 2], Y = [2, 1, 0]
reverso([], []).
reverso([H|T], L) :- reverso(T, X), append(X, [H], L).

/** <examples>
?- vazio([]).
?- vazio([1, 2, 3]).
?- pertence(2, [1, 2, 3]).
?- pertence(4, [1, 2, 3]).
?- somatorio([1, 2, 3, 4, 5], ResultadoSoma).
?- somatorio([], ResultadoSoma).
?- indice(2, [1, 2, 3, 4, 5], IndiceConsultado).
?- indice(6, [1, 2, 3, 4, 5], IndiceConsultado).
?- reverso([0, 1, 2], Y).
?- reverso([], Y).
*/
