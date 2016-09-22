Treating a String as **Graph** and then solving some interesting problems using **Depth First** traversal (and also avoiding generating substrings in the main code path of the solution, generating substrings is suboptimal)

A couple of problems that I have encountered are the following...

**Palindrome Decomposition**

As an example the string `abacusu` can be broken into the following

1. `a,b,a,c,u,s,u`
2. `a,b,a,c,usu`
3. `aba,c,u,s,u`

**Breaking a given string into all possible sentences containing valid words**

Consider the string `ilovedfstraversal`, and assume that a dictionary **D** of valid words is `D = {"i", "love", "d", "f", "s", "dfs", "traversal"}`

In this case the following sentences can be generated

1. `i love d f s traversal`
2.  `i love dfs traversal`

Both of the above problems have the same *foundation algorithm* i.e. pick a starting point in the string and then incrementally check all substrings of increasing lengths to check whether that substring ***is valid(per some rule)***, and then do this ***recursively*** for the remaining string ending at the ***valid*** substring OR the next character.

The things which differ for the above problems is the ***is valid(per some rule)*** part. For palindrome case, the rule of validity is that the substring must be a palindrome where for the *dictionary-word* case, the rule of validity is that the substring must be found in the specified dictionary.

Recursively generating substrings will solve the problem but I thought about avoiding going that path. Instead, *keep track of the substrings using another pointer array* and simply reuse the pointer array in depth first search.

In the code here, the search and validation are abstracted out completely. The consumer can supply a validator and pass it into the traversal/search logic. The traversal logic will simply callback into the validator and when one of the search path is terminated, it will also callback into a result collector which can do whatever it wants with the results.




