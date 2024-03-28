# **[start](https://www.zotero.org/support/start) > [dev](https://www.zotero.org/support/dev/start) > [web_api](https://www.zotero.org/support/dev/web_api/start) > [v3](https://www.zotero.org/support/dev/web_api/v3/start)** 

# Zotero Web API 文档

该页面记录了中可用的[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)读取请求，提供了对在线 Zotero 库的只读访问。

## 基本 URL

所有 API 请求的基本 URL 是


```
https://api.zotero.org
```

所有请求都必须使用 HTTPS.

## API 版本控制

API 有多个版本可用，生产代码应始终请求特定版本。本页记录了 API 版本 3，这是当前默认和推荐的版本。

客户端可以通过以下两种方式之一请求特定版本：

1. 通过 `Zotero-API-Version` HTTP 标头（ `Zotero-API-Version: 3`）
2. 通过 `v` 查询参数（ `v=3`）

建议生产代码 `Zotero-API-Version` 使用标头。 `v` 参数可用于更轻松地调试和共享 API 请求，或用于无法传递任意 HTTP 标头的客户端。

响应的 API 版本将在 `Zotero-API-Version` 响应标头中返回。

### 版本转换

大多数情况下，API 更改是以向后兼容的方式进行的。但是，有时可能需要进行向后不兼容的更改。发生这种情况时，将提供新的 API 版本，而不会更改未请求特定版本的客户端的默认版本。经过一段过渡期后，新的 API 版本将成为默认版本。如果 API 版本已停止，则请求已停止版本的客户端将收到最旧的可用版本。有关 API 版本转换的公告将始终提前发布[zotero-dev](https://groups.google.com/group/zotero-dev)。

## 身份验证

对公共库的读取访问不需要身份验证。

访问非公共库需要使用 API 密钥。第三方开发人员应该[使用 OAuth 建立凭据](https://www.zotero.org/support/dev/web_api/v3/oauth)或指示其用户创建专用密钥，以便与其服务一起使用。最终用户可以通过[他们的 Zotero 帐户设置](https://www.zotero.org/settings/keys/new)创建 API 密钥。

API 密钥可以通过以下三种方式之一包含在请求中：

1. 格式 `Zotero-API-Key: P9NiFoyLeZu2bZNvvuQPDWsd` 中的 HTTP 标头
2. 格式 `Authorization: Bearer P9NiFoyLeZu2bZNvvuQPDWsd` 中的 HTTP 标头
3. 作为 URL 查询参数，格式为 `key=P9NiFoyLeZu2bZNvvuQPDWsd`（不推荐）

建议使用 HTTP 标头，因为它允许使用从 API 返回的 URL（例如，用于分页）而无需修改。

 `Authorization: Bearer` 也是 OAuth 2.0 的身份验证机制。虽然 Zotero 目前仅支持 OAuth 1.0a，但当添加对 OAuth 2.0 的支持时，客户端将不再需要从 OAuth 响应中提取 API 密钥并将其单独传递给 API.

## 资源

### 用户和组库 URL

对特定库中数据的请求以 `/users/<userID>` 或 `/groups/<groupID>` 开头，以下称为 `<userOrGroupPrefix>`。用户 ID 不同于用户名，可以在[API Keys](https://www.zotero.org/settings/keys)页面和 OAuth 响应中找到。组 ID 与组名不同，可以从中 `/users/<userID>/groups` 检索。

#### 收藏

| 尤里                                          | Description                                                |
| :-------------------------------------------- | :--------------------------------------------------------- |
| <userOrGroupPrefix>/收藏                      | Collections in the library                                 |
| <userOrGroupPrefix>/收藏/顶部                 | Top-level collections in the library                       |
| <userOrGroupPrefix>/收藏/<collectionKey>      | A specific collection in the library                       |
| <userOrGroupPrefix>/集合/<collectionKey>/集合 | Subcollections within a specific collection in the library |

#### 项目

| URI                                                       | 描述                               |
| :-------------------------------------------------------- | :--------------------------------- |
| <userOrGroupPrefix>/items                                 | 库中的所有项目，不包括已丢弃的项目 |
| <userOrGroupPrefix>/items/top                             | 库中的顶级项目，不包括已丢弃的项目 |
| <userOrGroupPrefix>/items/trash                           | 垃圾桶中的物品                     |
| <userOrGroupPrefix>/items/<itemKey>                       | 库中的特定项目                     |
| <userOrGroupPrefix>/items/<itemKey>/children              | 特定项下的子项                     |
| <userOrGroupPrefix>/publications/items                    | 我的出版物中的项目                 |
| <userOrGroupPrefix>/collections/<collectionKey>/items     | 库中特定集合内的项目               |
| <userOrGroupPrefix>/collections/<collectionKey>/items/top | 库中特定集合内的顶级项目           |

#### 搜索

（注意：当前仅提供搜索元数据，而不提供搜索结果。）

| 尤里                                 | Description                            |
| :----------------------------------- | :------------------------------------- |
| <userOrGroupPrefix>/搜索             | All saved searches in the library      |
| <userOrGroupPrefix>/搜索/<searchKey> | A specific saved search in the library |

#### 标签

| 尤里                                                         | Description                                            |
| :----------------------------------------------------------- | :----------------------------------------------------- |
| <userOrGroupPrefix>/标记                                     | All tags in the library                                |
| <userOrGroupPrefix>/标签/<url+encoded+tag>                   | Tags of all types matching a specific name             |
| <userOrGroupPrefix>/项目/<itemKey>/标签                      | Tags associated with a specific item                   |
| <userOrGroupPrefix>/集合/<collectionKey>/标签                | Tags within a specific collection in the library       |
| <userOrGroupPrefix>/items/tags                               | 库中的所有标记，能够根据项目进行筛选                   |
| <userOrGroupPrefix>/items/top/tags                           | Tags assigned to top-level items                       |
| <userOrGroupPrefix>/项目/垃圾/标签                           | Tags assigned to items in the trash                    |
| <userOrGroupPrefix>/集合/<collectionKey>/项目/标签           | Tags assigned to items in a given collection           |
| <userOrGroupPrefix>/collection/<collectionKey>/items/top/tags | Tags assigned to top-level items in a given collection |
| <userOrGroupPrefix>/出版物/项目/标签                         | Tags assigned to items in My Publications              |

### 其他 URL

| URI                    | 描述                                                         |
| :--------------------- | :----------------------------------------------------------- |
| /keys/<key>            | 给定 API 密钥的用户 ID 和权限。使用 `DELETE` HTTP 方法删除密钥。这通常只能由最初使用[OAuth](https://www.zotero.org/support/dev/web_api/v3/oauth)创建密钥的客户端来完成。 |
| /users/<userID>/groups | 当前 API 密钥有权访问的组的集合，包括密钥所有者所属的公共组，即使密钥没有这些组的显式权限。 |

## 读取请求

以下参数影响从读取请求返回的数据的格式。所有参数都是可选的。

### 一般参数

以下参数对所有读取请求均有效：

| Parameter | 价值观                                                       | Default                                                      | Description                                                  |
| :-------- | :----------------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| `format`  | `atom`, `bib`, `json`, `keys`, `versions`, [export format](https://www.zotero.org/support/dev/web_api/v3/basics#export_formats) | `json` (or `atom` if the `Accept` header includes `application/atom+xml`) | `atom` 将返回一个适合在提要阅读器或提要读取库中使用的 Atom 提要。仅对项目请求 `bib` 有效，将以 XHTML 格式返回格式化的书目。 `bib` 模式当前限制为最多 150 个项目。 `json` 将为多对象请求返回一个 JSON 数组，为单对象请求返回一个 JSON 对象。对于多对象请求 `keys` 有效，将返回一个以换行符分隔的对象键列表。 `keys` 模式没有默认或最大限制。对多对象集合、项目和搜索请求 `versions` 有效，将返回一个 JSON 对象，其中 Zotero 对象键作为键，对象版本作为值。例如 `keys`， `versions` 模式没有默认或最大限制。导出格式（仅对项请求有效）以指定的格式生成输出。 |

### “format=JSON ”的参数

| Parameter | 价值观                                                       | Default | Description                                                  |
| :-------- | :----------------------------------------------------------- | :------ | :----------------------------------------------------------- |
| `include` | `bib`, `citation`, `data`, [export format](https://www.zotero.org/support/dev/web_api/v3/basics#export_formats) Multiple formats can be specified by using a comma as the delimiter (`include=data,bib`). | `data`  | 响应中包含的格式： `bib` 仅对项请求有效，将为每个项返回格式化的引用。仅对项目请求 `citation` 有效，将为每个项目返回格式化的引文。 `data`（默认）将包括 JSON 格式的所有可写字段，适合修改并发送回 API.导出格式（仅对项请求有效）将为每个项返回指定格式的数据。 |

### “format=atom ”的参数

| Parameter | 价值观                                                       | Default | Description                                                  |
| :-------- | :----------------------------------------------------------- | :------ | :----------------------------------------------------------- |
| `content` | `bib`, `citation`, `html`, `json`, [export formats](https://www.zotero.org/support/dev/web_api/v3/basics#export_formats), `none` Multiple formats can be specified by using a comma as the delimiter (`content=json,bib`). | `html`  | Atom 响应的 `<content>` 节点的格式： `html`（默认）将返回每个对象的 XHTML 表示，这对于在提要阅读器中显示和通过 XML 工具进行解析非常有用。 `json` 当前仅对项目和集合请求有效，将返回所有项目字段的 JSON 表示。仅对项请求 `bib` 有效，将为每个项返回格式化的引用。仅对项目请求 `citation` 有效，将为每个项目返回格式化的引文。导出格式（仅对项请求有效）将为每个项返回指定格式的数据。如果不需要其他数据，请使用 `none` 减少响应大小。如果请求多种格式，则 `<content>` 将包含多个 `<zapi:subcontent>` 元素（在http://zotero.org/ns/api 命名空间中），每个元素都具有 `zapi:type` 与指定的内容参数之一匹配的属性。 |

### “format=bib ”、“include/content=bib ”、“include/content=citation ”的参数

| Parameter  | Values  | Default                     | 描述                                                         |
| :--------- | :------ | :-------------------------- | :----------------------------------------------------------- |
| `style`    | string  | `chicago-note-bibliography` | 用于格式化参考的引用样式。可以是（例如 `apa`）中[Zotero 风格仓库](https://www.zotero.org/styles/)某个样式的文件名（不带.CSL 扩展名），也可以是远程 CSL 文件的 URL. |
| `linkwrap` | boolean | `0`                         | 设置为 `1` 以链接形式返回 URL 和 DOI                         |
| `locale`   | string  | `en-US`                     | 书目区域设置。请参见[可用的 CSL 区域设置](https://github.com/citation-style-language/locales)。请注意，某些样式使用固定的区域设置，无法进行本地化。 |

请注意和 `include=bib`/ `content=bib` 之间 `format=bib` 的区别。 `format=bib` 返回 XHTML 格式的书目，根据所选样式的规则进行排序。 `include=bib`（仅对 `format=json`（默认格式模式）和 `format=atom` 有效）在 JSON 块 `data` 或 Atom 块 `<content>` 中为每个项目返回单独的格式化引用，并根据查询参数对结果或提要进行排序。 `format=bib` 处理你请求的整个提要，而不考虑任何限制参数，因此通常最好只将其与集合或标记一起使用。

### 项目导出格式

以下书目数据格式可用作 `format` 项目请求的、 `include` 和 `content` 参数：

| Parameter           | 描述                                                         |
| :------------------ | :----------------------------------------------------------- |
| `bibtex`            | 比布特克斯                                                   |
| `biblatex`          | 比布拉泰克斯                                                 |
| `bookmarks`         | Netscape 书签文件格式                                        |
| `coins`             | 硬币                                                         |
| `csljson`           | [引文样式语言数据格式](https://github.com/citation-style-language/schema/blob/master/csl-data.json) |
| `csv`               | CSV                                                          |
| `mods`              | MODS                                                         |
| `refer`             | 请参阅/bibix                                                 |
| `rdf_bibliontology` | [书目本体论](http://bibliontology.com/)RDF                   |
| `rdf_dc`            | 不合格的都柏林核心 RDF                                       |
| `rdf_zotero`        | 佐特罗 RDF                                                   |
| `ris`               | 里斯                                                         |
| `tei`               | 文本编码倡议（Tei）                                          |
| `wikipedia`         | 维基百科引用模板                                             |

## 搜索

### 搜索参数

| Parameter  | 价值观                                                       | Default | Description                                                  |
| :--------- | :----------------------------------------------------------- | :------ | :----------------------------------------------------------- |
| `itemKey`  | string                                                       | null    | 以逗号分隔的项键列表。仅对项目请求有效。在单个请求中最多可以指定 50 个项目。 |
| `itemType` | [搜索语法](https://www.zotero.org/support/dev/web_api/v3/basics#search_syntax) | null    | Item type search                                             |
| `q`        | string                                                       | null    | 快速搜索。默认情况下，搜索标题和单个创建者字段。使用 `qmode` 参数更改模式。当前仅支持短语搜索。 |
| `since`    | integer                                                      | `0`     | 仅返回在指定库版本之后修改的对象，这些对象在以前 `Last-Modified-Version` 的标头中返回。有关详细信息，请参阅[Syncing](https://www.zotero.org/support/dev/web_api/v3/syncing)。 |
| `tag`      | [搜索语法](https://www.zotero.org/support/dev/web_api/v3/basics#search_syntax) | null    | Tag search                                                   |

### 搜索参数（项目和端点）

| Parameter        | Values                           | Default                  | 描述                                                         |
| :--------------- | :------------------------------- | :----------------------- | :----------------------------------------------------------- |
| `includeTrashed` | `0`, `1`                         | `0` (except in `/trash`) | 在废纸篓中包含项目                                           |
| `qmode`          | `titleCreatorYear`, `everything` | `titleCreatorYear`       | 快速搜索模式。要包含全文内容，请使用 `everything`。其他领域的搜索将在未来成为可能。 |

### 搜索参数（标记和端点）

| Parameter | Values                   | Default    | 描述                                                  |
| :-------- | :----------------------- | :--------- | :---------------------------------------------------- |
| `qmode`   | `contains`, `startsWith` | `contains` | 快速搜索模式。要执行左边界搜索，请使用 `startsWith`。 |

### 搜索参数（项内标签端点）

当返回项目中的标签时，这些参数可用于搜索项目。在这种情况下，主要参数（ `q`， `qmode`， `tag`）应用于标记本身，作为请求的主要对象。

| Parameter   | 价值观                                                       | Default    | Description                           |
| :---------- | :----------------------------------------------------------- | :--------- | :------------------------------------ |
| `itemQ`     | 串                                                           | null       | Same as `q` in an `items` request     |
| `itemQMode` | `contains`， `startsWith`                                    | `contains` | Same as `qmode` in an `items` request |
| `itemTag`   | [搜索语法](https://www.zotero.org/support/dev/web_api/v3/basics#search_syntax) | null       | Same as `tag` in an `items` request   |

### 搜索语法

 `itemType` 和 `tag` 参数支持布尔搜索：

示例：

-  `itemType=book`
-  `itemType=book || journalArticle`（或）
-  `itemType=-attachment`（不是）

-  `tag=foo`
-  `tag=foo bar`（带空格的标记）
-  `tag=foo&tag=bar`（和）
-  `tag=foo bar || bar`（或）
-  `tag=-foo`（不是）
-  `tag=\-foo`（文字第一个字符连字符）

如果你的客户端或库需要，请确保对搜索字符串进行 URL 编码。

## 排序和分页

### 排序和分页参数

以下参数仅对多对象读取请求（如 `<userOrGroupPrefix>/items`）有效，但不支持排序或分页的 `format=bib` 请求除外。

| Parameter   | 价值观                                                       | Default                               | Description                                                  |
| :---------- | :----------------------------------------------------------- | :------------------------------------ | :----------------------------------------------------------- |
| `sort`      | `dateAdded` “”， `dateModified`， `title`， `creator` `itemType` `date` `publisher` `publicationTitle` `journalAbbreviation` `language` `accessDate` `libraryCatalog` `callNumber` `rights` `addedBy`，、，、，、 `numItems`（标签）" | `dateModified` (`dateAdded` for Atom) | The name of the field by which entries are sorted            |
| `direction` | `asc`, `desc`                                                | varies by `sort`                      | 参数中 `sort` 指定的字段的排序方向                           |
| `limit`     | integer 1-100*                                               | `25`                                  | 单个请求返回的最大结果数。导出格式需要。                     |
| `start`     | integer                                                      | `0`                                   | 第一个结果的索引。与“限制”参数结合使用，以选择可用结果的切片。 |

#### 总结果

多对象读取请求的响应将包括自定义 HTTP 标头， `Total-Results` 该标头提供与请求匹配的结果总数。给定响应中提供的结果的实际数量将不超过 100。

#### 链接头

当读取请求匹配的结果总数大于当前限制时，API 将在 HTTP `Link` 标头中包含分页链接。可能的值有 `rel=first`、 `rel=prev`、 `rel=next` 和 `rel=last`。对于某些请求，标题可能还包括 `rel=alternate` Zotero 网站上相关页面的链接。


```
GET https://api.zotero.org/users/12345/items?limit=30
Link: <https://api.zotero.org/users/12345/items?limit=30&start=30>; rel="next",
 <https://api.zotero.org/users/12345/items?limit=30&start=5040>; rel="last",
 <https://www.zotero.org/users/12345/items>; rel="alternate"
```

（为清楚起见，此处插入了新行。）

## 缓存

为了有效地使用 API，客户端应尽可能发出条件 GET 请求。多对象请求（例如 `/users/1/items`）返回 `Last-Modified-Version` 具有当前库版本的标头。如果 `If-Modified-Since-Version: <libraryVersion>` 使用后续多对象读取请求传递标头，并且自指定版本以来库中的数据未发生更改，则 API 将返回 `304 Not Modified` 而不是 `200`。（目前不支持单对象条件请求，但将来会支持。）

虽然返回 A `304` 的条件 GET 请求应该很快，但某些客户端可能希望或需要自己执行额外的缓存，在向 Zotero API 发出后续条件请求之前使用存储的数据一段时间。当已知底层 Zotero 数据不会频繁更改时，或者当数据将被频繁访问时，这尤其有意义。例如，显示 Zotero 集合中的书目的网站可能会将返回的书目缓存一个小时，之后它将向 Zotero API 发出另一个条件请求。如果 API 返回 A `304`，网站将继续显示缓存的书目一个小时，然后重试。这将阻止网站在每次用户加载页面时向 Zotero API 发出请求。

除了发出条件请求之外，下载整个 Zotero 库的数据的客户端应该使用 `?since=` 仅请求自上次下载数据以来已更改的对象。

有关库和对象版本控制的详细信息，请参阅[Syncing](https://www.zotero.org/support/dev/web_api/v3/syncing)。

## 速率限制

*[目前并非所有利率限制都是强制执行的，但客户应准备好应对这些限制。]*

访问 Zotero API 的客户端应该准备好处理两种形式的速率限制：回退请求和硬限制。

如果 API 服务器过载，则 API 可以在响应中包括 `Backoff: <seconds>` HTTP 报头，指示客户端应当执行保持数据一致性所需的最小数量的请求，然后在所指示的秒数内停止做出进一步的请求。 `Backoff` 可以包括在任何响应中，包括成功的响应。

如果客户端在给定的时间段内发出了太多的请求，API 可能会返回 `429 Too Many Requests` 一个 `Retry-After: <seconds>` 标头。接收 `429` 的客户端应等待标头中指示的秒数，然后重试请求。

当服务器正在进行维护时，也 `Retry-After` 可以包含在 `503 Service Unavailable` 响应中。

## 示例 GET 请求和响应

读取请求 URL 及其响应的几个示例：

| Multi-Object JSON Response: Top-Level Items In A Collection |                                                              |
| :---------------------------------------------------------- | ------------------------------------------------------------ |
| Request                                                     | https://api.zotero.org/users/475425/collections/9KH9TNSJ/items/top?v=3 |
| Response                                                    | https://gist.github.com/6eeace93a5c29775d39c                 |

| Single-Object JSON Response: Individual Item |                                                        |
| :------------------------------------------- | ------------------------------------------------------ |
| Request                                      | https://api.zotero.org/users/475425/items/X42A7DEE?v=3 |
| Response                                     | https://gist.github.com/f1030b9609aadc51ddec           |

| Multi-Object JSON Response: Collections For A User |                                                     |
| :------------------------------------------------- | --------------------------------------------------- |
| Request                                            | https://api.zotero.org/users/475425/collections?v=3 |
| Response                                           | https://gist.github.com/0bc17ca64ee7d3bc9063        |

| Atom Feed: Items In A Library |                                                           |
| :---------------------------- | --------------------------------------------------------- |
| Request                       | https://api.zotero.org/users/475425/items?format=atom&v=3 |
| Response                      | https://gist.github.com/24172188ea79efa210b5              |

| Formatted Bibliography: Items In A Collection |                                                              |
| :-------------------------------------------- | ------------------------------------------------------------ |
| Request                                       | https://api.zotero.org/users/475425/collections/9KH9TNSJ/items?format=bib |
| Response                                      | https://gist.github.com/77bc2413cce4c219f862                 |

## HTTP 状态代码

成功的 GET 请求将返回 `200 OK` 状态代码。

[条件 GET 请求](https://www.zotero.org/support/dev/web_api/v3/basics#caching)可能会回来 `304 Not Modified`。

对于任何读取或写入请求，服务器可能会针对无效请求返回 `400 Bad Request`、 `404 Not Found` 或 `405 Method Not Allowed`，并 `500 Internal Server Error` 针对与服务器相关的问题返回或 `503 Service Unavailable`。[身份验证](https://www.zotero.org/support/dev/web_api/v3/basics#authentication)错误（例如，无效的 API 密钥或权限不足）将返回 `403 Forbidden`。

传递 `Expect` 不受支持的标头将导致 `417 Expectation Failed` 响应。

[库/对象版本控制](https://www.zotero.org/support/dev/web_api/v3/syncing#version_numbers)或[Zotero-write-token](https://www.zotero.org/support/dev/web_api/v3/write_requests#zotero-write-token)错误将导致 `412 Precondition Failed` 或 `428 Precondition Required`。

 `429 Too Many Requests` 表示客户端已[速率受限](https://www.zotero.org/support/dev/web_api/v3/basics#rate_limiting)。



# Zotero Web API 项目类型/字段请求

[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)客户端要向其用户呈现编辑 UI，必须知道 Zotero 项目类型、字段和创建者类型的有效组合。客户端可以从 Zotero API 请求此数据。

由于目前很少进行模式更改，因此客户端应将类型/字段数据缓存一段时间（例如，一个小时），而不进行进一步的请求。然后，对新数据的后续请求应该包括 `If-Modified-Since` 包含来自原始响应 `Last-Modified` 的标头内容的标头。如果未发生任何更改，服务器将返回一个 `304 Not Modified`，客户端应在相同的时间段内继续使用缓存数据。*[条件请求–即 `If-Modified-Since` –尚未实现。]*

默认情况下，将以英文返回用户友好的类型/字段名称。客户端可以通过传递 `locale` 参数（例如 `GET/itemTypes?locale=fr-FR`）来请求其他语言的名称。

注意：整个架构（包括所有地区的翻译）也可以作为单个文件https://api.zotero.org/schema. 下载，请参阅[模式的 GitHub 存储库](https://github.com/zotero/zotero-schema)以获取缓存说明。

### 获取所有项类型


```
GET /itemTypes
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
[
  { "itemType" : "book", "localized" : "Book" },
  { "itemType" : "note", "localized" : "Note" },
  (…)
]
```

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `200 OK`           |                                                   |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | 不支持区域设置。                                  |

### 正在获取所有项目字段


```
GET /itemFields
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
[
  { "field" : "title", "localized" : "Title" },
  { "field" : "url", "localized" : "URL" },
  (…)
]
```

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `200 OK`           |                                                   |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | 不支持区域设置。                                  |

### 获取项目类型的所有有效字段

注意：打算写入服务器的 API 使用者通常应结合使用[/items/new](https://www.zotero.org/support/dev/web_api/v3/types_and_fields#getting_a_template_for_a_new_item)，[/itemTypes](https://www.zotero.org/support/dev/web_api/v3/types_and_fields#getting_all_item_types)而不是使用此请求。


```
GET /itemTypeFields?itemType=book
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
[
  { "field" : "title", "localized" : "Title" },
  { "field" : "abstractNote", "localized" : "Abstract" },
  (…)
]
```

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `200 OK`           |                                                   |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | 不支持区域设置。                                  |

### 获取项类型的有效创建者类型


```
GET /itemTypeCreatorTypes?itemType=book
[
  { "creatorType" : "author", "localized" : "Author" },
  { "creatorType" : "editor", "localized" : "Editor" },
  (…)
]
```

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `200 OK`           |                                                   |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | “ItemType ”未指定或无效。不支持区域设置。         |

### 获取本地化的创建者字段


```
GET /creatorFields
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
[
  { "field" : "firstName", "localized" : "First" },
  { "field" : "lastName", "localized" : "Last" },
  { "field" : "name", "localized" : "Name" }
]
```

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | 不支持区域设置。                                  |

### 获取新项目的模板


```
GET /items/new?itemType=book
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
{
  "itemType" : "book",
  "title" : "",
  "creators" : [
    {
      "creatorType" : "author",
      "firstName" : "",
      "lastName" : ""
    }
  ],
  "url" : "",
  (...),
  "tags" : [],
  "collections" : [],
  "relations" : {}
}
GET /items/new?itemType=note
If-Modified-Since: Mon, 14 Mar 2011 22:30:17 GMT
{
  "itemType" : "note",
  "note" : "",
  "tags" : [],
  "collections" : [],
  "relations" : {}
}
```

TODO：附件创建（请参阅[文件上传](https://www.zotero.org/support/dev/web_api/v3/file_upload)）

| Common Responses   |                                                   |
| :----------------- | ------------------------------------------------- |
| `200 OK`           |                                                   |
| `304 Not Modified` | 自那时以来没有发生 `If-Modified-Since` 任何变化。 |
| `400 Bad Request`  | `itemType` 未指定或无效。                         |



本页介绍了的[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)写入方法。有关访问 API 的基本信息，请参阅[Basics](https://www.zotero.org/support/dev/web_api/v3/basics)页面，包括此处未列出的可能的 HTTP 状态代码。

对给定库具有写访问权限的[API key](https://www.zotero.org/support/dev/web_api/v3/basics#authentication)是使用写方法所必需的。

## JSON 对象数据

默认情况下，从模式中 `format=json` 的 API 返回的对象包括一个 `data` 包含“可编辑 JSON”的属性—即所有可以修改并发送回服务器的对象字段：


```
{
  "key": "ABCD2345",
  "version": 1,
  "library": { ... },
  "links": { ... },
  "meta": { ... },
  "data": {
    "key": "ABCD2345",
    "version": 1,
    "itemType": "webpage",
    "title": "Zotero Quick Start Guide",
    "creators": [
        {
            "creatorType": "author",
            "name": "Center for History and New Media"
        }
    ],
    "abstractNote": "",
    "websiteTitle": "Zotero",
    "websiteType": "",
    "date": "",
    "shortTitle": "",
    "url": "https://www.zotero.org/support/quick_start_guide",
    "accessDate": "2014-06-12T21:28:55Z",
    "language": "",
    "rights": "",
    "extra": "",
    "dateAdded": "2014-06-12T21:28:55Z",
    "dateModified": "2014-06-12T21:28:55Z",
    "tags": [],
    "collections": [],
    "relations": {}
  }
}
```

有两种方法可以对提供的 JSON 进行更改：

### 纲领性办法

对于对 API 的编程访问，推荐的方法是从 `data` 属性中提取可编辑的 JSON，根据需要进行更改，然后仅将可编辑的 JSON 上传回 API.对于新项目，[空模板](https://www.zotero.org/support/dev/web_api/v3/types_and_fields#getting_a_template_for_a_new_item)可以从 API 中检索可编辑的 JSON.

这种方法通过只发送服务器实际处理的数据来减少上传带宽。（为了实现更高效的上载，可以使用下面讨论的 HTTP `PATCH` 方法来仅发送已更改的字段。）本文档中的示例采用编程访问。

### 休息方法

对于更随意的访问，API 支持标准的 REST 行为，允许重新上传整个下载的 JSON.这允许在不编写一行代码的情况下执行编辑：


```
$ URL="https://api.zotero.org/users/1234567/items"
$ API_KEY="P9NiFoyLeZu2bZNvvuQPDWsd"
$ curl -H "Zotero-API-Key: $API_KEY" $URL > items.json
$ vi items.json  # edit the item data
$ curl -H "Zotero-API-Key: $API_KEY" -d @items.json -v $URL
```

在这个示例中，一个 JSON 项目数组被保存到一个文本文件中，在文本编辑器中进行修改，然后回发到相同的 URL.

该方法允许仅使用 cURL 和文本编辑器来执行诸如批编辑之类的复杂任务。在文本文件中修改的任何对象都将在服务器上更新，而未修改的对象将保持不变。

对于单个对象的 PUT，可以使用类似的过程：


```
$ URL="https://api.zotero.org/users/1234567/items/ABCD2345"
$ API_KEY="P9NiFoyLeZu2bZNvvuQPDWsd"
$ curl -H "Zotero-API-Key: $API_KEY" $URL > item.json
$ vi items.json  # edit the item data
$ curl -H "Zotero-API-Key: $API_KEY" -X PUT -d @item.json -v $URL
```

请注意，上传完整的 JSON 时，只 `data` 处理属性。忽略所有其他属性（ `library`、 `links`、 `meta` 等）。

## 项目请求

### 创建项目

创建新条目时，首先使用[项模板请求](https://www.zotero.org/support/dev/web_api/v3/types_and_fields#getting_a_template_for_a_new_item)获取条目类型的空 JSON（或使用模板的缓存版本）。然后修改它并将其重新提交到阵列中的服务器：


```
POST <userOrGroupPrefix>/items
Content-Type: application/json
Zotero-Write-Token: <write token> or If-Unmodified-Since-Version: <last library version>
[
  {
    "itemType" : "book",
    "title" : "My Book",
    "creators" : [
      {
        "creatorType":"author",
        "firstName" : "Sam",
        "lastName" : "McAuthor"
      },
      {
        "creatorType":"editor",
        "name" : "John T. Singlefield"
      }
    ],
    "tags" : [
      { "tag" : "awesome" },
      { "tag" : "rad", "type" : 1 }
    ],
    "collections" : [
      "BCDE3456", "CDEF4567"
    ],
    "relations" : {
      "owl:sameAs" : "http://zotero.org/groups/1/items/JKLM6543",
      "dc:relation" : "http://zotero.org/groups/1/items/PQRS6789",
      "dc:replaces" : "http://zotero.org/users/1/items/BCDE5432"
    }
  }
]
```

除、 `tags`、 `collections` 和 `relations` 以外 `itemType` 的所有属性都是可选的。

| Common Responses               |                                                  |
| :----------------------------- | ------------------------------------------------ |
| `200 OK`                       | 请求已完成。有关各个写入的状态，请参阅响应 JSON. |
| `400 Bad Request`              | 无效类型/字段；无法解析的 JSON                   |
| `409 Conflict`                 | 目标库已锁定。                                   |
| `412 Precondition Failed`      | 提供 `Zotero-Write-Token` 的已提交。             |
| `413 Request Entity Too Large` | 提交的项目太多                                   |

 `200 OK` 响应：


```
{
  "success": {
    "0": "<itemKey>"
  },
  "unchanged": {},
  "failed": {},
  }
}
```

有关响应格式的详细信息，请参阅[创建多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#creating_multiple_objects)。

### 创建多个项目

看吧[创建多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#creating_multiple_objects)。

### 更新现有项目

要更新现有项，请首先检索该项的当前版本：


```
GET <userOrGroupPrefix>/items/<itemKey>
```

可编辑数据（类似于上面中[创建项目](https://www.zotero.org/support/dev/web_api/v3/write_requests#creating_an_item)显示的项数据）将在响应的属性中 `data` 找到。

API 支持两种修改项目数据的方法：上传完整的项目数据（ `PUT`）或仅发送更改的数据（ `PATCH`）。

#### 全项更新（PUT）

使用 `PUT`，你可以将项目的完整可编辑 JSON 提交给服务器，通常是通过直接修改下载的可编辑 JSON（即 `data` 属性的内容）并重新提交：


```
PUT <userOrGroupPrefix>/items/<itemKey>
Content-Type: application/json
{
  "key": "ABCD2345",
  "version": 1,
  "itemType" : "book",
  "title" : "My Amazing Book",
  "creators" : [
    {
      "creatorType":"author",
      "firstName" : "Sam",
      "lastName" : "McAuthor"
    },
    {
      "creatorType":"editor",
      "name" : "Jenny L. Singlefield"
    }
  ],
  "tags" : [
    { "tag" : "awesome" },
    { "tag" : "rad", "type" : 1 }
  ],
  "collections" : [
    "BCDE3456", "CDEF4567"
  ],
  "relations" : {
    "owl:sameAs" : "http://zotero.org/groups/1/items/JKLM6543",
    "dc:relation" : "http://zotero.org/groups/1/items/PQRS6789",
    "dc:replaces" : "http://zotero.org/users/1/items/BCDE5432"
  }
}
```

除、 `tags`、 `collections` 和 `relations` 以外 `itemType` 的所有属性都是可选的。任何未指定的现有字段都将从项目中删除。如果 `creators`、 `tags`、 `collections` 或 `relations` 为空，则将从项目中删除任何关联的创建者/标记/集合/关系。

#### 部分项更新（修补程序）

使用 `PATCH`，你可以只提交实际发生更改的属性，以获得可能更高效的操作。上传的 JSON 中未包含的属性在服务器上保持不变。若要清除属性，请根据需要传递空字符串或空数组。


```
PATCH <userOrGroupPrefix>/items/<itemKey>
If-Unmodified-Since-Version: <last item version>
{
  "date" : "2013"
  "collections" : [
    "BCDE3456", "CDEF4567"
  ]
}
```

这将向项添加一个 `date` 字段，并将其添加到两个指定的集合中（如果尚未存在）。数组属性被解释为完整列表，因此省略集合键将导致该项从该集合中删除。

通过 `POST` 时[修改多个项目](https://www.zotero.org/support/dev/web_api/v3/write_requests#updating_multiple_objects)，该 `PATCH` 行为也可用。

#### PUT 和 PATCH

通过将父项的键分配给 `parentItem` 属性，可以将注释和附件设置为子项。如果在同一 `POST` 请求中创建了父项和子项，则子项必须出现在项数组中的父项之后，并在本地创建[item key](https://www.zotero.org/support/dev/web_api/v3/write_requests#object_keys)。

项的当前版本号包含在 `version` JSON 属性中，也包含在单项请求的 `Last-Modified-Version` 头中。 `PUT` 并且 `PATCH` 请求必须在 `version` 属性或 `If-Unmodified-Since-Version` 标头中包含项的当前版本号。（ `version` 包含在来自 API 的响应中，因此只需修改可编辑数据的客户端不需要为版本头而烦恼。）如果检索到该项后，该项已在服务器上发生更改，则写入请求将被拒绝并出现 `412 Precondition Failed` 错误，并且必须从服务器检索该项的最新版本，然后才能进行更改。有关库和对象版本控制的详细信息，请参阅[版本号](https://www.zotero.org/support/dev/web_api/v3/syncing#version_numbers)。

| Common Responses          |                                                  |
| :------------------------ | ------------------------------------------------ |
| `204 No Content`          | 已成功更新该项目。                               |
| `400 Bad Request`         | 无效类型/字段；无法解析的 JSON                   |
| `409 Conflict`            | 目标库已锁定。                                   |
| `412 Precondition Failed` | 检索后项目已更改（即，提供的项目版本不再匹配）。 |

### 正在更新多个项目

看吧[更新多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#updating_multiple_objects)。

### 删除项目


```
DELETE <userOrGroupPrefix>/items/<itemKey>
If-Unmodified-Since-Version: <last item version>
```

| Common Responses            |                                                  |
| :-------------------------- | ------------------------------------------------ |
| `204 No Content`            | 已删除该项目。                                   |
| `409 Conflict`              | 目标库已锁定。                                   |
| `412 Precondition Failed`   | 检索后项目已更改（即，提供的项目版本不再匹配）。 |
| `428 Precondition Required` | `If-Unmodified-Since-Version` 未提供。           |

### 删除多个项目

在单个请求中最多可以删除 50 个项目。


```
DELETE <userOrGroupPrefix>/items?itemKey=<key>,<key>,<key>
If-Unmodified-Since-Version: <last library version>
204 No Content
Last-Modified-Version: <library version>
```

| Common Responses            |                                        |
| :-------------------------- | -------------------------------------- |
| `204 No Content`            | 项目已删除。                           |
| `409 Conflict`              | 目标库已锁定。                         |
| `412 Precondition Failed`   | 自指定版本后，库已更改。               |
| `428 Precondition Required` | `If-Unmodified-Since-Version` 未提供。 |

## 收集请求

### 创建集合


```
POST <userOrGroupPrefix>/collections
Content-Type: application/json
Zotero-Write-Token: <write token>
[
  {
    "name" : "My Collection",
    "parentCollection" : "QRST9876"
  }
]
```

| Common Responses          |                                                              |
| :------------------------ | ------------------------------------------------------------ |
| `200 OK`                  | 请求已完成，没有出现一般错误。有关各个写入的状态，请参阅响应 JSON. |
| `409 Conflict`            | 目标库已锁定。                                               |
| `412 Precondition Failed` | 提供的 Zotero-Write-Token 已提交。                           |

### 更新现有集合


```
GET <userOrGroupPrefix>/collections/<collectionKey>
```

可编辑的 JSON 将在 `data` 属性中找到。


```
PUT <userOrGroupPrefix>/collections/<collectionKey>
Content-Type: application/json
{
  "key" : "DM2F65CA",
  "version" : 156,
  "name" : "My Collection",
  "parentCollection" : false
}
```

| Common Responses          |                                                        |
| :------------------------ | ------------------------------------------------------ |
| `200 OK`                  | 集合已更新。                                           |
| `409 Conflict`            | 目标库已锁定。                                         |
| `412 Precondition Failed` | 自检索以来，集合已更改（即，提供的集合版本不再匹配）。 |

### 集合项成员资格

可以通过 `collections` Item JSON 中的属性将项目添加到集合或从集合中删除。

### 删除收藏


```
DELETE <userOrGroupPrefix>/collections/<collectionKey>
If-Unmodified-Since-Version: <last collection version>
```

| Common Responses          |                                                        |
| :------------------------ | ------------------------------------------------------ |
| `204 No Content`          | 已删除集合。                                           |
| `409 Conflict`            | 目标库已锁定。                                         |
| `412 Precondition Failed` | 自检索以来，集合已更改（即，所提供的 ETag 不再匹配）。 |

### 删除多个集合

在单个请求中最多可以删除 50 个集合。


```
DELETE <userOrGroupPrefix>/collections?collectionKey=<collectionKey>,<collectionKey>,<collectionKey>
If-Unmodified-Since-Version: <last library version>
204 No Content
Last-Modified-Version: <library version>
```

| Common Responses          |                          |
| :------------------------ | ------------------------ |
| `204 No Content`          | 已删除集合。             |
| `409 Conflict`            | 目标库已锁定。           |
| `412 Precondition Failed` | 自指定版本后，库已更改。 |

## 已保存的搜索请求

### 创建搜索


```
POST <userOrGroupPrefix>/searches
Content-Type: application/json
Zotero-Write-Token: <write token>
[
  {
    "name": "My Search",
    "conditions": [
      {
        "condition": "title",
        "operator": "contains",
        "value": "foo"
      },
      {
        "condition": "date",
        "operator": "isInTheLast",
        "value": "7 days"
      }
    ]
  }
]
```

| Common Responses          |                                                              |
| :------------------------ | ------------------------------------------------------------ |
| `200 OK`                  | 请求已完成，没有出现一般错误。有关各个写入的状态，请参阅响应 JSON. |
| `409 Conflict`            | 目标库已锁定。                                               |
| `412 Precondition Failed` | 提供的 Zotero-Write-Token 已提交。                           |

### 删除多个搜索

在单个请求中最多可以删除 50 个搜索。


```
DELETE <userOrGroupPrefix>/searches?searchKey=<searchKey>,<searchKey>,<searchKey>
If-Unmodified-Since-Version: <last library version>
204 No Content
Last-Modified-Version: <library version>
```

| Common Responses          |                          |
| :------------------------ | ------------------------ |
| `204 No Content`          | 搜索已删除。             |
| `409 Conflict`            | 目标库已锁定。           |
| `412 Precondition Failed` | 自指定版本后，库已更改。 |

## 标记请求

### 删除多个标记

在一个请求中最多可以删除 50 个标签。


```
DELETE <userOrGroupPrefix>/tags?tag=<URL-encoded tag 1> || <URL-encoded tag 2> || <URL-encoded tag 3>
If-Unmodified-Since-Version: <last library version>
204 No Content
Last-Modified-Version: <library version>
```

## 多对象请求

### 创建多个对象

通过在数组中包含多个对象，可以在单个请求中创建多达 50 个集合、保存的搜索或项目：


```
POST <userOrGroupPrefix>/collections
Content-Type: application/json
[
  {
    "name" : "My Collection",
    "parentCollection": "QRST9876"
  },
  {
    "name": "My Other Collection"
  }
]
```

对于[syncing](https://www.zotero.org/support/dev/web_api/v3/syncing)具有预定键的对象，[object key](https://www.zotero.org/support/dev/web_api/v3/write_requests#object_keys)还可以提供新的对象。

 `200` 响应：


```
Content-Type: application/json
Last-Modified-Version: <library version>
{
  "successful": {
    "0": "<saved object>",
    "2": "<saved object>"
  },
  "unchanged": {
    "4": "<objectKey>"
  }
  "failed": {
    "1": {
      "key": "<objectKey>",
      "code": <HTTP response code>,
      "message": "<error message>"
    },
    "3": {
      "key": "<objectKey>",
      "code": <HTTP response code>,
      "message": "<error message>"
    },
  }
}
```

 `successful`、 `unchanged` 和 `failed` 对象的键是上载的数组中 Zotero 对象的数字索引。 `Last-Modified-Version` 是已分配给对象中 `successful` 任何 Zotero 对象的版本—即在此请求中修改的对象。

| Common Responses          |                                                              |
| :------------------------ | ------------------------------------------------------------ |
| `200 OK`                  | 对象已上载。                                                 |
| `409 Conflict`            | 目标库已锁定。                                               |
| `412 Precondition Failed` | 中 `If-Unmodified-Since-Version` 提供的版本已过期，或者提供 `Zotero-Write-Token` 的已提交。 |

#### 更新多个对象

在单个请求中最多可以更新 50 个集合、保存的搜索或项目。

请按照中的[创建多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#creating_multiple_objects)说明进行操作，但要在每个对象中包含 `key` 和 `version` 属性。如果修改从 API 返回的可编辑 JSON，则这些属性已经存在，不应修改。作为单个 `version` 属性的替代，最后已知的库版本可以通过 `If-Unmodified-Since-Version` HTTP 头传递。

项目还可以包括 `dateAdded` 和 `dateModified` 包含时间戳的[ISO 8601 格式](http://en.wikipedia.org/wiki/ISO_8601)属性（例如，“2014-06-10T13：52：43Z”）。如果 `dateAdded` 包含在现有项中，则它必须与现有值 `dateAdded` 匹配，否则 API 将返回 400 错误。如果现有项目的更新中未包含新 `dateModified` 时间，则该项目的 `dateModified` 值将设置为当前时间。从 API 返回的可编辑 JSON 以正确的格式包含 `dateAdded` 和 `dateModified`，因此满足服务器设置的修改时间的客户端可以简单地忽略这些属性。


```
POST <userOrGroupPrefix>/collections
Content-Type: application/json
[
  {
    "key": "BD85JEM4",
    "version": 414,
    "name": "My Collection",
    "parentCollection": false
  },
  {
    "key": "MNC5SAPD",
    "version": 416
    "name": "My Subcollection",
    "parentCollection": "BD85JEM4"
  }
]
```

响应与中[创建多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#creating_multiple_objects)的响应相同。

请注意 `POST`，下面是 `PATCH` 语义，这意味着任何未指定的属性都将在服务器上保持不变。要删除现有属性，请将其包含在空字符串中或 `false` 将其作为值。

## 对象键

虽然服务器将自动为上载的对象生成有效密钥，但在某些情况下，例如在同一请求中创建父项和子项时[syncing](https://www.zotero.org/support/dev/web_api/v3/syncing)，可能需要或有必要在本地创建对象密钥。

本地对象键应符合该模式 `/[23456789ABCDEFGHIJKLMNPQRSTUVWXYZ]{8}/`。

## Zotero-write-token


```
Zotero-Write-Token: 19a4f01ad623aa7214f82347e3711f56
```

 `Zotero-Write-Token` 是一个可选的 HTTP 标头，包含客户端生成的随机 32 个字符的标识符字符串，可以将其包含在无版本的写入请求中，以防止它们被多次处理（例如，如果用户单击表单提交按钮两次）。Zotero 服务器将成功请求的写入令牌缓存 12 小时，使用同一写入令牌的同一 API 密钥的后续请求将被拒绝，并 `412 Precondition Failed` 显示状态码。如果请求失败，则不会存储写入令牌。

如果使用版本化的写请求（即包含 `If-Unmodified-Since-Version` HTTP 标头或单个对象 `version` 属性的请求），则 `Zotero-Write-Token` 是多余的，应省略。

## 例子

请参阅[Syncing](https://www.zotero.org/support/dev/web_api/v3/syncing)页面上的示例工作流，该工作流将读取和写入方法组合在一起，以便通过 API 完整高效地同步 Zotero 数据。

# Zotero Web API 文件上传

除了提供访问[read](https://www.zotero.org/support/dev/web_api/v3/basics#read_requests)和[write](https://www.zotero.org/support/dev/web_api/v3/write_requests)在线图书馆数据的方法外，Zotero Web API 还允许你上传附件文件。

确切的过程取决于你是添加新的附件文件还是修改现有的附件文件，以及你是执行完全上载还是上载二进制差异。

## 1A）创建新附件

### 我。获取附件项模板


```
GET /items/new?itemType=attachment&linkMode={imported_file,imported_url,linked_file,linked_url}
{
  "itemType": "attachment",
  "linkMode": "imported_url",
  "title": "",
  "accessDate": "",
  "url": "",
  "note": "",
  "tags": [],
  "relations": {},
  "contentType": "",
  "charset": "",
  "filename": "",
  "md5": null,
  "mtime": null
}
```

### 二、创建子附件项目


```
POST /users/<userID>/items
Content-Type: application/json
Zotero-Write-Token: <token>
[
  {
    "itemType": "attachment",
    "parentItem": "ABCD2345",
    "linkMode": "imported_url",
    "title": "My Document",
    "accessDate": "2012-03-14T17:45:54Z",
    "url": "http://example.com/doc.pdf",
    "note": "",
    "tags": [],
    "relations": {},
    "contentType": "application/pdf",
    "charset": "",
    "filename": "doc.pdf",
    "md5": null,
    "mtime": null
  }
]
```

 `md5` 并且 `mtime` 可以直接在个人库中进行编辑，以实现基于 WebDAV 的文件同步。在使用 Zotero 文件存储时，不应直接编辑它们，Zotero 文件存储提供了一种原子方法（下面将详细介绍）来设置属性以及相应的文件。

可以通过排除 `parentItem` 属性或将其设置为 `false` 来创建顶级附件。尽管 API 允许将所有附件作为顶级项目以实现向后兼容性，但建议仅允许文件附件（ `imported_file`/ `linked_file`）和 PDF 导入的 Web 附件（ `imported_url` 具有内容类型 `application/pdf`）作为顶级项目，如 Zotero 客户端中一样。

## 1b）修改现有附件

### 我。检索附件信息


```
GET /users/<userID>/items/<itemKey>
{
  "key": "ABCD2345",
  "version": 124,
  "library": { ... },
  ...
  "data": {
    "key": "ABCD2345",
    "version": 124,
    "itemType": "attachment",
    "linkMode": "imported_file",
    "title": "My Document",
    "note": "",
    "tags": [],
    "relations": {},
    "contentType": "text/plain",
    "charset": "utf-8",
    "filename": "doc.txt",
    "md5": "4fa38e3f2c360ca181e633d02bab91f5",
    "mtime": "1331171741767"
  }
}
```

### 二、下载现有文件


```
GET /users/<userID>/items/<itemKey>/file
```

检查 `ETag` 响应的标题以确保它与附件项的 `md5` 值匹配。如果不匹配，请再次检查附件项。如果附件项目仍然具有不同的哈希，则文件的最新版本可能只能通过 WebDAV 而不能通过 Zotero 文件存储获得，并且由客户端决定如何继续。

将文件另存为 `filename`，并将修改时间设置为 `mtime` 附件项中提供的。

### 三、对文件进行更改

请注意，要使用二进制差异执行更快的部分上载，必须在进行更改之前保存文件的副本。

## 2) 获取上传授权


```
POST /users/<userID>/items/<itemKey>/file
Content-Type: application/x-www-form-urlencoded
If-None-Match: *
md5=<hash>&filename=<filename>&filesize=<bytes>&mtime=<milliseconds>
```

对于现有附件，请使用 `If-Match: <hash>` 代替 `If-None-Match: *`，其中<hash>是文件以前的 MD5 哈希值（下载时在 `ETag` 标题中提供）。

请注意， `mtime` 必须以毫秒为单位，而不是以秒为单位。

成功 `200` 的响应将返回两个可能的 JSON 对象之一：


```
{
  "url": ...,
  "contentType": ...,
  "prefix": ...,
  "suffix": ...,
  "uploadKey": ...
}
```

或


```
{ "exists": 1 }
```

在后一种情况下，文件已存在于服务器上，并且已成功与指定的项关联。无需采取进一步行动。

| Common Responses               |                                                              |
| :----------------------------- | ------------------------------------------------------------ |
| `200 OK`                       | 上载已授权或文件已存在。                                     |
| `403 Forbidden`                | 文件编辑被拒绝。                                             |
| `409 Conflict`                 | 目标库已锁定。                                               |
| `412 Precondition Failed`      | 自检索以来，文件已远程更改（即，提供的 ETag 不再匹配）。冲突解决留给客户端。 |
| `413 Request Entity Too Large` | 上载将超过库所有者的存储配额。                               |
| `428 Precondition Required`    | 未提供 If-Match 或 If-None-Match.                            |
| `429 Too Many Requests`        | 太多未完成的上载。在 `Retry-After` 标头中指定的秒数后重试。  |

## 3A）完全上传

### 我。POST 文件

连接 `prefix`，文件内容， `suffix` 并将其发布到 `url`，同时将 `Content-Type` 标题设置为 `contentType`。

 `prefix` 和 `suffix` 是包含多部分/格式数据的字符串。在某些环境中，直接使用表单参数可能更容易。添加 `params=1` 到上面的上载授权请求中，以检索数组中 `params` 的各个参数，这些参数将替换 `contentType`、 `prefix` 和 `suffix`。

| Common Responses |                  |
| :--------------- | ---------------- |
| `201 Created`    | 文件已成功上载。 |

### 二、注册上传


```
POST /users/<userID>/items/<itemKey>/file
Content-Type: application/x-www-form-urlencoded
If-None-Match: *
upload=<uploadKey>
```

对于现有附件，使用 `If-Match: <hash>`，其中<hash>是文件的上一个 MD5 哈希，作为附件项中的 `md5` 属性提供。

| Common Responses          |                                                          |
| :------------------------ | -------------------------------------------------------- |
| `204 No Content`          | 上载已成功注册。                                         |
| `412 Precondition Failed` | 自检索以来，文件已远程更改（即，提供的 ETag 不再匹配）。 |

注册上载后，附件项将反映新的元数据（ `filename`、 `mtime`、 `md5`）。

## 3B）部分上传


```
PATCH /users/<userID>/items/<itemKey>/file?algorithm={xdelta,vcdiff,bsdiff}&upload=<uploadKey>
If-Match: <previous-value-of-md5-property>
<Binary diff of old and new versions>
```

为了获得最佳效果，我们建议使用带有“ `-9 -S djw`”标志的 XDelta 版本 3. BSDIFF 生成差异的时间要长得多。“VCDIFF”是“XDELTA”的别名，因为 XDELTA3 可以处理 VCDIFF 格式的差异。

如果用户的代理服务器不支持 HTTP 修补程序（理论上由 A `405 Method Not Allowed` 指示），则客户端可能希望自动回退到完整上载（可能带有某种形式的警告）。

上载完成后，附件项将反映新的元数据。

| Common Responses            |                                                             |
| :-------------------------- | ----------------------------------------------------------- |
| `204 No Content`            | 已成功应用修补程序。                                        |
| `409 Conflict`              | 目标库被锁定；修补文件与提供的 MD5 哈希或文件大小不匹配     |
| `428 Precondition Required` | 未提供 If-Match 或 If-None-Match.                           |
| `429 Too Many Requests`     | 太多未完成的上载。在 `Retry-After` 标头中指定的秒数后重试。 |





# Zotero Web API 全文内容请求

本页介绍了通过[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)访问 Zotero 项目的全文内容的方法。有关访问 API 的基本信息，请参阅[Basics](https://www.zotero.org/support/dev/web_api/v3/basics)页面，包括此处未列出的可能的 HTTP 状态代码。

### 获取新的全文内容


```
GET <userOrGroupPrefix>/fulltext?since=<version>
Content-Type: application/json
Last-Modified-Version: <library version>
{
    "<itemKey>": <version>,
    "<itemKey>": <version>,
    "<itemKey>": <version>
}
```

对于全文内容版本高于本地存储版本的每个项目，获取该项目的全文内容，如下所述。

| Common Responses  |                      |
| :---------------- | -------------------- |
| `200 OK`          | 已成功检索全文内容。 |
| `400 Bad Request` | 未提供“since ”参数。 |

### 获取项目的全文内容


```
GET <userOrGroupPrefix>/items/<itemKey>/fulltext
```

 `<itemKey>` 应与现有附件项相对应。


```
Content-Type: application/json
Last-Modified-Version: <version of item's full-text content>
{
    "content": "This is full-text content.",
    "indexedPages": 50,
    "totalPages": 50
}
```

 `indexedChars` 和 `totalChars` 用于文本文档，而 `indexedPages` 和 `totalPages` 用于 PDF.

| Common Responses |                                            |
| :--------------- | ------------------------------------------ |
| `200 OK`         | 找到给定项目的全文内容。                   |
| `404 Not Found`  | 找不到该项目，或找不到给定项目的全文内容。 |

### 设置项目的全文内容


```
PUT <userOrGroupPrefix>/items/<itemKey>/fulltext
Content-Type: application/json
{
    "content": "This is full-text content.",
    "indexedChars": 26,
    "totalChars": 26
}
```

 `<itemKey>` 应与现有附件项相对应。

对于文本文档，包括 `indexedChars` 和 `totalChars`。对于 PDF，包括 `indexedPages` 和 `totalPages`。

| Common Responses  |                                |
| :---------------- | ------------------------------ |
| `204 No Content`  | 已更新项目的全文内容。         |
| `400 Bad Request` | 提供的 JSON 无效。             |
| `404 Not Found`   | 找不到该项目或该项目不是附件。 |

### 按全文内容搜索项目

请参见和 `q` `qmode` [搜索参数](https://www.zotero.org/support/dev/web_api/v3/basics#search_parameters)。



# Zotero Web API 同步

本文档概述了将客户端与 Zotero 服务器同步[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)的推荐步骤。请确保你已经阅读了[写请求](https://www.zotero.org/support/dev/web_api/v3/write_requests)有关通过 API 修改数据的基本信息的文档。

待办事项：

- 合并[WebSocket 处理](https://www.zotero.org/support/dev/web_api/v3/streaming_api)

## 同步属性

除标准对象元数据（项字段值、组名等）外，客户端还应存储以下属性：

- 每个组的元数据的版本号
- 每个库的版本号
- 每个 SynCable 对象的版本号和布尔 `synced` 标志
- 无法处理且应明确请求的已下载对象的列表（无论其远程版本号如何）（可选；有关详细信息，请参见[处理保存错误](https://www.zotero.org/support/dev/web_api/v3/syncing#handling_save_errors)）

## 版本号

服务器上的每个 Zotero 库和对象（集合、项目等）都有一个关联的版本号。版本号可用于确定客户端是否具有库或对象的最新数据，从而实现高效且安全的同步。

该 API 支持三个公开版本的自定义 HTTP 标头： `Last-Modified-Version` 响应标头以及 `If-Unmodified-Since-Version` 和 `If-Modified-Since-Version` 请求标头。标头适用的版本号取决于发出的请求：对于多对象请求（如） `<userOrGroupPrefix>/items` `<userOrGroupPrefix>/items/<itemKey>`，标头适用于整个库，而对于单对象请求（如），标头适用于单个对象。

版本号还可以通过其他几种方式访问，如下所述。

版本号保证单调递增，但不保证顺序递增，客户端应将其视为不透明的整数值。

#### 上次修改版本

 `Last-Modified-Version` 响应标头指示库（对于多对象请求）或单个对象（对于单对象请求）的当前版本。如果在写请求中对库进行了更改，则库的版本号将增加，在同一请求中修改的任何对象都将被设置为新的版本号，并且新的版本号将在 `Last-Modified-Version` 标头中返回。

#### if-modified-since-version

 `If-Modified-Since-Version` 请求标头可用于有效地检查新数据。如果 `If-Modified-Since-Version: <libraryVersion>` 通过多对象读取请求传递，并且自指定版本以来库中的数据未发生更改，则 API 将返回 `304 Not Modified`。如果 `If-Modified-Since-Version: <objectVersion>` 通过单个对象读取请求传递，则如果单个对象未更改，则 `304 Not Modified` 将返回。

#### if-unmodified-since-version

 `If-Unmodified-Since-Version` 请求标头用于确保现有数据不会被客户端用过期数据覆盖。修改现有对象的所有写入请求必须包含每个对象的 `If-Unmodified-Since-Version: <version>` 标头或[JSON 版本属性](https://www.zotero.org/support/dev/web_api/v3/syncing#json_version_property)。如果两者都省略，API 将返回一个 `428 Precondition Required`。

对于对多对象终结点（如 `<userOrGroupPrefix>/items`）的写入请求，如果库在传递的版本之后进行了修改，则 API 将返回 `412 Precondition Failed`。对于对单个对象端点（如）的 `<userOrGroupPrefix>/items/<itemKey>` 写入请求，如果对象自传递的版本以来已被修改，则 API 将返回 `412`。

客户端通常只有在下载了正在写入的对象类型的所有服务器数据后，才应对多对象请求使用 `If-Unmodified-Since-Version`。否则，创建新对象的客户端可能会分配服务器上已存在的对象密钥，并意外地覆盖现有对象。

 `If-Unmodified-Since-Version` 还支持更高效的同步。有更改要上传的客户端不应首先轮询远程更新，而应首先尝试执行必要[写请求](https://www.zotero.org/support/dev/web_api/v3/syncing#iv_upload_modified_data)的操作，在 `If-Unmodified-Since-Version` 头文件中传递当前的本地库版本。如果更新的数据可用，API 将返回 `412 Precondition Failed`，指示客户端必须首先检索更新的数据。在没有写入请求的 `412` 情况下，具有本地修改的客户端不需要显式地检查远程更改。

 `If-Unmodified-Since-Version: <version>` 替换以前单个对象写入所需的 `If-Match: <etag>` 标头。

#### JSON 版本属性

 `format=json` 响应将在每个对象的可编辑 JSON 中包含一个 `version` 属性（ `data` 属性），指示该对象的当前版本。此值将与在 JSON 对象的顶级提供的 `version` 属性相同。对于单对象请求，这也将与 `Last-Modified-Version` 响应标头的值相同。

如果包含在提交回 API 的 JSON 中，则 JSON 版本属性的行为将等效于单个对象 `If-Unmodified-Since-Version`：如果对象自指定版本以来已被修改，则 API 将返回 `412 Precondition Failed`。在编写包含对象键的对象时，请求必须包含 `If-Unmodified-Since-Version` JSON 版本属性，或者每个对象必须包含 JSON 版本属性。在不使用 `If-Unmodified-Since-Version` 的请求中使用对象键写入新对象时，请使用特殊版本 0 来指示对象不应存在于服务器上。

虽然 `If-Unmodified-Since-Version` 和 JSON 版本属性对于写请求不是互斥的，但它们是冗余的，通常客户端应该根据其交互机制使用其中一个。有关可能的同步方法的讨论，请参阅[部分库同步](https://www.zotero.org/support/dev/web_api/v3/syncing#partial-library_syncing)。

#### ？因为 =<version>

 `since` 查询参数可用于仅检索自特定版本以来修改的对象。

#### ？格式 = 版本

 `format=versions` 类似于 `format=keys`，但它不返回以换行符分隔的对象键列表，而是返回一个 JSON 对象，该对象具有以对象键为键的对象版本：


```
{
  "<itemKey>": <version>,
  "<itemKey>": <version>,
  "<itemKey>": <version>
}
```

例如 `format=keys`， `format=versions` 不受最大结果数的限制，默认情况下返回所有匹配对象。

#### 本地对象版本

下面描述了同步期间本地对象版本的使用以及更新它们的过程。

当用户在常规使用期间在本地创建或修改对象时，设置 `synced = false` 为指示下次同步时需要上载对象。为新对象提供版本 0. 在同步进程之外修改对象时，不要更改版本。

## 全库同步

以下步骤用于完全同步 Zotero 库，例如启用完全离线使用。 有关替代同步方法的提示，请参阅[部分库同步](https://www.zotero.org/support/dev/web_api/v3/syncing#partial_library_syncing)。

### 1) 验证密钥访问

````
GET /keys/current
````

`200` 响应：

````
{
  {
    "userID": 12345
    "username": "Z User"
    "access": {
        "user": {
            "library": true
            "files": true
            "notes": true
            "write": true
        }
        "groups": {
            "all": {
                "library": true
                "write": true
            }
        }
    }
}
}
````

`/keys/current` 返回有关 `Zotero-API-Key` 标头中提供的 API 密钥的信息。 使用此响应来验证密钥是否具有对您尝试访问的库的预期访问权限。 如有必要，显示警告，提示用户不再具有足够的访问权限，并提出删除本地库或重置本地更改。

### 2) 获取更新的组元数据

组元数据包括组标题和描述以及成员/角色/权限信息。 它与组库数据分开。

首先，检索用户组的列表，其中的版本指示每个组元数据的当前状态：

````
GET /users/<userID>/groups?format=versions
````

`200` 响应：

````
{
  "<groupID>": "<version>",
  "<groupID>": "<version>",
  "<groupID>": "<version>"
}
````

删除列表中未包含的所有本地组，这些组已被删除或当前无法访问。 （用户可能已从组中删除，或者当前的 API 密钥可能不再具有访问权限。）如果在任何不再可用的组中本地修改了数据，请为用户提供取消修改数据并将其传输到其他地方的功能 在继续之前。

对于本地不存在或具有不同版本号的每个组，检索组元数据：

````
GET /groups/<groupID>
Last-Modified-Version: <version>
JSON response with metadata
````

更新本地组元数据和版本号。

### 3）同步库数据

对每个库执行以下步骤：

#### 我。获取更新数据

**注意：**对上传进行更改的客户端应首先尝试[上传数据](https://www.zotero.org/support/dev/web_api/v3/syncing#iv_upload_modified_data)，只有在收到`412 Precondition Failed``时才检索更新的数据`。有关详细信息，请参阅[If-Unmodified-Since-Version ](https://www.zotero.org/support/dev/web_api/v3/syncing#if-unmodified-since-version)。

使用每个对象类型的适当请求，检索自上次检查该对象类型以来更改的所有对象的版本：

```
GET <userOrGroupPrefix>/collections?since=<version>&format=versions 
GET <userOrGroupPrefix>/searches?since=<version>&format=versionsGET <userOrGroupPrefix>/items/top?since=<version>&format=versions&includeTrashed=1GET <userOrGroupPrefix >/items?since=<版本>&format=版本&includeTrashed=1
<version>``是`上次成功完成同步过程时从 API 返回的最终`Last-Modified-Version ``，或者`在第一次同步库时为`0 ``。
```

（ `since``参数也可以`由不下载所有项目并希望保持库中所有标签列表最新的客户端在`…/tags`请求（不带`format=versions ``）`上使用。它不是下载所有项目的客户端需要直接请求更新的标签，因为项目对象包含所有关联的标签。）

第一个请求（例如，针对集合版本）还可以包含`If-Modified-Since-Version: <last saving library version>`标头。如果 API 返回`304 Not Modified `，则自指定版本以来，任何对象类型的库数据都没有发生更改，并且不需要发出进一步的请求来检索数据，除非[之前有失败的对象](https://www.zotero.org/support/dev/web_api/v3/syncing#handling_save_errors)需要重试。

`200`响应：

```
上次修改版本：<版本> 
[“<objectKey>”：<版本>，“<objectKey>”：<版本> “<objectKey>”：<版本>，]
```

对于每个返回的对象，将版本与该对象的本地版本进行比较。如果远程版本不匹配，则将对象排队以供下载。通常，所有返回的对象都应具有较新的版本号，但在某些情况下，例如完全同步（即， `since=0 `）或中断同步，客户端可能会检索本地已更新的对象的版本。该版本还将匹配第二个非`/top`上的顶级项目 `items`请求，因为顶级项目已经被处理。

[检索排队](https://www.zotero.org/support/dev/web_api/v3/syncing#handling_save_errors)的对象以及之前标记为无法按键保存的对象，一次最多 50 个：

```
GET <用户或组前缀>/collections?collectionKey=<键>,<键>,<键>,<键> 
GET <用户或组前缀>/searches?searchKey=<键>,<键>,<键>,<键>GET < userOrGroupPrefix>/items?itemKey=<key>,<key>,<key>,<key>&includeTrashed=1
```

项目响应包括创建者、标签、集合关联和关系。

处理远程更改：

```
对于每个更新的对象：
如果本地不存在对象：使用版本=上次修改版本创建本地对象并设置synced = true继续如果对象尚未在本地修改（synced == true）：用synced = true覆盖并且 version = Last-Modified-Version 否则：如果对象未更改，则执行冲突解决：设置synced = true 且 version = Last-Modified-Version 否则，如果可以自动合并更改：从每一方应用更改并设置synced = true并且 version = Last-Modified-Version 其他：如果用户选择远程复制，则提示用户选择一侧或合并冲突：用synced = true 覆盖，version = Last-Modified-Version 否则，如果用户选择本地复制：synced = false 并设置完成后重新启动同步的标志
```

##### 解决冲突

冲突解决是一个复杂的过程，此处未详细描述，但请参阅 Zotero 客户端代码以获取示例。

一些显着的特点：

1. 当对象成功下载或上传时，Zotero 客户端会将API 响应中的`数据`块保存为与对象版本相关的原始 JSON。当同步期间发生冲突时，它可以将对象的本地和远程版本与原始 JSON 进行比较，以确定每一侧进行了哪些更改，并自动合并不冲突的更改。系统只会提示用户手动解决同一字段的冲突更改。
2. Zotero 客户端会自动解决项目以外的对象的冲突，而不提示用户，从而在恢复已删除的数据方面犯了错误。
3. 恢复本地删除的集合是一种特殊情况。项目成员身份是项目的属性，因此在集合恢复后，本地项目将不再是集合的成员，并且本地项目也可能已与集合一起删除。恢复本地删除的集合时，Zotero 客户端从 API 获取集合的项目，然后将它们添加回集合并将它们标记为未同步（如果它们仍然存在于本地）或从本地删除日志中删除它们并将它们标记为手动下载（如果没有）。

##### 处理保存错误

如果在处理对象时发生错误（例如，由于本地数据库中的外键约束），可以通过以下两种方式之一进行处理：

1. 将错误视为致命错误并停止同步而不更新本地库版本
2. 将对象密钥添加到稍后需要下载的对象列表中，然后继续同步，最后更新本地库版本，就像同步成功一样。在将来的同步中，将此列表上的对象添加到从`版本请求`返回的对象集中，以便即使远程版本低于`?since=``中指定的库版本，也会再次请求其数据`。按退避计划重试这些对象，因为它们可能需要服务器端修复或客户端更新才能成功保存。上传本地更改的对象时，请跳过此列表中的对象，因为已知它们已过期并会导致`412`错误。如果这些对象后来显示为远程删除，请将它们从对象列表中删除。

处理一组对象时，维护同步运行的进程队列并将失败的对象移动到队列末尾可能会有所帮助，以防它们依赖于正在检索的其他对象。 （在许多情况下，可以预先对对象进行排序以避免此类错误，例如通过在子集合之前对父集合进行排序。）如果处理队列的循环完成而没有成功处理任何对象，则停止同步。

#### 二.获取已删除的数据

```
GET <用户或组前缀>/deleted?since=<版本>
```

如上所述， `<version>``是`上次成功同步运行期间从 API 返回的`Last-Modified-Version ``。`

回复：

```
内容类型：application/json
最后修改版本：<version>{"collections": [ "<collectionKey>"],"searches": [ "<searchKey>"],"items": [ "<itemKey> ", "<itemKey>"],"tags": [ "<tagName>", "<tagName>"]}
```

处理远程删除：

```
对于 ['collections', 'searches', 'items'] 中每个已删除的对象：
如果本地对象不存在： 继续 如果对象尚未在本地修改 (synced == true)： 删除本地对象，跳过删除日志else: 如果用户选择删除，则执行冲突解决，删除本地对象，如果用户选择本地修改，则跳过删除日志，保留对象并设置synced = true和version = Last-Modified-Version
```

Zotero 客户端会自动解决项目以外的对象的冲突，而不提示用户，从而在恢复已删除的数据方面犯了错误。

#### 三.检查并发远程更新

对于来自 API 的每个响应，检查`Last-Modified-Version`以查看自第一个请求返回的`Last-Modified-Version``以来它是否已更改（例如，`` collections?since= `）。如果有，则重新启动检索更新和删除的数据的过程，在重新启动之间等待越来越长的时间，以便让其他客户端有机会完成。

保存所有远程更改且在此过程中远程版本没有更改后，将上次运行的`Last-Modified-Version``保存为新的本地库版本。`

#### 四.上传修改数据

`同步`设置为`false ``的`对象。按照[更新多个对象](https://www.zotero.org/support/dev/web_api/v3/write_requests#updating_multiple_objects)中的说明进行操作，将当前库版本作为`If-Unmodified-Since-Version``传递`。

创建者、标签和关系包含在项目对象中，并且不会单独同步。

`200``响应`中，为每个成功上传的 Zotero 对象设置`synced = true`和`version = Last-Modified-Version `，并将`Last-Modified-Version``存储`为当前库版本，以便与下一个写入请求一起传递。不要更新`未更改的对象`中的 Zotero 对象的版本。重试非致命故障。

在出现`412 Precondition Failed`响应时，返回到该库的同步过程的开头，在重新启动之间等待越来越长的时间。

#### v. 上传本地删除内容

当正常使用过程中本地删除对象时，将其库和密钥添加到删除日志中。同步时，发送日志中对象的删除请求，并在成功删除后从日志中清除它们。当解决本地删除的对象和远程修改的对象之间的冲突以支持远程对象时，请将其从删除日志中删除。

有关特定请求，请参阅[删除多个集合](https://www.zotero.org/support/dev/web_api/v3/write_requests#deleting_multiple_collections)、[删除多个搜索](https://www.zotero.org/support/dev/web_api/v3/write_requests#deleting_multiple_searches)和[删除多个项目。](https://www.zotero.org/support/dev/web_api/v3/write_requests#deleting_multiple_items)将当前库版本作为`If-Unmodified-Since-Version``传递`。

请求示例：

```
删除 <userOrGroupPrefix>/collections?collectionKey=<key>,<key>,<key> 
If-Unmodified-Since-Version: <版本>
```

回复：

```
204 无内容
上次修改版本：<版本>
```

在`204`响应中，将返回的`Last-Modified-Version``存储`为当前库版本，以便与下一个写入请求一起传递。

在收到`412 Precondition Failed`响应时，返回到该库的同步过程的开头。

## 部分库同步

上述步骤是为同步后应始终包含用户 Zotero 数据的完整本地副本的客户端设计的。虽然这对于永久安装的客户端可能有意义，但对于其他用例来说不太理想，例如提供对库的临时访问的客户端或通常通过移动连接进行连接的客户端，其中下载库中的所有数据将非常慢或昂贵。选择性同步需要对上述步骤进行一些修改。下面概述了三种可能的方法：

### 固定收藏清单

此方法适用于允许用户选择要同步的集合子集的客户端，但在其他方面的行为类似于完全离线客户端。

客户端仍然只需要跟踪单个库版本，但不是从`<userOrGroupPrefix>/items?format=versions&since=<version>``下载所有项目的列表`，而是从每个选定的集合中单独检索项目列表请求例如`<userOrGroupPrefix>/collections/<collectionKey/items?format=versions&since=<version> `。仅当所有集合中的项目都已下载（或以持久方式排队下载）时，才会更新本地库版本。

### 每个集合的版本

这种方法适用于仅响应用户交互（例如单击集合）而加载数据的客户端，而不是加载一组预定义的集合。

客户端需要跟踪每个视图的单独库版本，以表示该视图中所有对象的状态。如果上传到多对象端点（例如`<userOrGroupPrefix>/items``）`导致`412 `，表明库中的某些内容（尽管不一定在视图中）已更改，则客户端只需要获取新数据（或视图中包含新数据的对象列表）并更新与视图关联的版本号。请注意，这样的版本号将与视图对象（例如集合）本身的版本号分开。

客户端还需要跟踪代表集合/搜索列表状态的版本号。 （虽然他们可以简单地重新加载整个集合列表，但对于拥有许多集合的用户来说，这样做会很慢。）

### 单对象版本

最后一种方法是完全避开库范围的版本号，仅使用单个对象版本来上传数据。这可以通过使用`If-Unmodified-Since-Version`标头的单对象端点或使用 JSON 版本属性的多对象端点来完成。由于对象的可编辑 JSON 包含对象版本，因此将收到的 JSON 传递回服务器的客户端将自动获得安全更新。这可以被认为是默认的API使用模式。

请注意，多对象端点应始终用于大型操作。过度使用单对象端点可能会导致服务器限制。

# Zotero 流媒体 API

Zotero 流 API 通过 WebSockets 为 Zotero 库更改提供基于推送的通知，允许在库中的数据更改或用户加入或离开库时几乎即时更新。

请注意，此 API 提供库级别的更改通知。它不直接提供更新的数据。收到库更改通知的 API 使用者应使用其标准[同步流程](https://www.zotero.org/support/dev/web_api/v3/syncing)来检索数据，确保手动和自动同步具有单一、一致的代码路径。

为了避免错过更新，客户端应该连接到流 API，然后在连接后触发标准同步操作，以使其自身更新到库的当前版本。

## 要求

### 创建一个空的 WebSocket 流

```
var ws = new WebSocket('wss://stream.zotero.org');
```

服务器响应：

```
{“事件”：“已连接”，“重试”：10000}
```

### 将订阅添加到事件流

客户留言：

```
{ 
“action”：“createSubscriptions”，“订阅”：[{“apiKey”：“abcdefghijklmn1234567890”，“主题”：[“/users/123456”，“/groups/234567”，“/groups/345678”]} , { "apiKey": "bcdefghijklmn12345678901" }, { "主题": [ "/groups/456789", "/groups/567890" ] } ]}
```

服务器响应：

```
{ 
“事件”：“订阅创建”，“订阅”：[{“apiKey”：“abcdefghijklmn1234567890”，“主题”：[“/users/123456”，“/groups/234567”]}，{“apiKey”：“ bcdefghijklmn2345678901", "主题": [ "/users/345678" ] }, { "主题": [ 
"/groups/456789" 
] } ], "错误": [ { "apiKey": "abcdefghijklmn1234567890", "主题" : "/groups/345678", "error": "主题对于提供的 API 密钥无效" }, { "topic": "/groups/567890", "error": "没有 API 密钥就无法访问主题" } ]}
```

指定 API 密钥的所有主题订阅（新的和现有的）都包含在响应中。不包括当前请求中未添加的先前添加的 API 密钥的订阅。无需指定 API 密钥即可订阅公共主题，新添加的主题将在响应中分组在一起。

如果未为 API 密钥提供`主题`属性，则连接将接收该密钥可用的所有主题的事件，并自动[跟踪](https://www.zotero.org/support/dev/web_api/v3/streaming_api#key_access_tracking)该密钥的可用主题的更改。

`createSubscriptions`删除主题订阅。如果给定 API 密钥的订阅已存在，则提供的主题将与现有主题合并。如果提供空`主题数组，则不会进行任何更改。`如果未提供`主题`属性，密钥将升级为自动跟踪访问，如上所述。

#### 错误

| **4413 请求实体太大** | **订阅数量（包括现有订阅）将超出每个连接的限制** |
| --------------------- | ------------------------------------------------ |
|                       |                                                  |

### 接收现有事件流上的事件

```
{“事件”：“topicUpdated”，“主题”：“/users/123456”，“版本”：678} 
{“事件”：“topicAdded”，“apiKey”：“abcdefghijklmn1234567890”，“主题”：“/groups /345678"} {"event": "topicRemoved", "apiKey": "abcdefghijklmn1234567890", "topic": "/groups/234567"}
```

### 删除给定 API 密钥的所有订阅

客户留言：

```
{ 
“action”：“deleteSubscriptions”，“订阅”：[{“apiKey”：“abcdefghijklmn1234567890”}]}
```

服务器响应：

```
{ 
“事件”：“订阅已删除”}
```

#### 错误

| **4409 冲突** | **此连接上不存在使用给定   API 密钥或主题的订阅** |
| ------------- | ------------------------------------------------- |
|               |                                                   |

### 删除特定的 API 密钥/主题对

客户留言：

```
{ 
“action”：“deleteSubscriptions”，“订阅”：[{“apiKey”：“abcdefghijklmn1234567890”，“主题”：“/users/123456”}]}
```

服务器响应：

```
{ 
“事件”：“订阅已删除”}
```

如果从自动跟踪主题的键中手动删除主题，则生成的主题列表将被修复，并且该键将不再接收`topicAdded`事件。如果密钥失去对主题的访问权限，它仍可能收到`topicRemoved``事件。`

#### 错误

| **4409 冲突** | **此连接上不存在使用给定   API 密钥和/或主题的订阅** |
| ------------- | ---------------------------------------------------- |
|               |                                                      |

### 删除公共主题订阅

客户留言：

```
{ 
"action": "deleteSubscriptions", "subscriptions": [ { "topic": "/users/123456" } ]}
```

服务器响应：

```
{ 
“事件”：“订阅已删除”}
```

#### 错误

| **4409 冲突** | **此连接上不存在给定主题的公共订阅** |
| ------------- | ------------------------------------ |
|               |                                      |

## 按键访问跟踪

对于没有指定主题的 API 密钥，连接将跟踪密钥的访问并接收该密钥可用的所有主题的事件。

例如，如果密钥的所有者加入某个组，并且该密钥有权访问该用户的所有组，则连接将接收`topicAdded``事件，并`在组中的数据发生更改时开始接收`topicUpdated``事件。`

·     

#  OAuth 密钥交换

除了用户从 zotero.org 帐户设置手动创建 Zotero API 密钥外，Zotero 还支持[OAuth ](http://oauth.net/)1.0a 进行 API 密钥交换。

## 注册您的应用程序

为了开始使用 OAuth 代表用户创建 API 密钥，您必须[向 Zotero 注册您的应用程序](https://www.zotero.org/oauth/apps)以获得客户端密钥和客户端密钥，以便在您的应用程序/网站和 zotero.org 之间将来的所有 OAuth 握手期间使用。请注意，在您获取特定用户的 API 密钥后，进一步的 Zotero API 请求不需要这些客户端凭据。

## 请求特定权限

您可以通过在 OAuth 交换期间将值作为 URL 中的 GET 值发送到新密钥表单来请求允许您的应用程序获得特定权限。为用户预填充的可能值是：

·    名称（密钥的描述）

·    Library_access（1 或 0 - 允许对个人图书馆项目的读取访问）

·    Notes_access（1 或 0 - 允许读取个人图书馆笔记）

·    write_access（1或0 - 允许对个人库的写访问）

·    all_groups（无、读取或写入 - 允许对所有当前和未来组的访问级别）

·    Identity=1 不创建密钥。相反，仅使用 OAuth 交换来获取用户的身份信息，以便执行不需要特殊权限的操作。

## 使用 OAuth 握手进行密钥交换

用于访问 Zotero API 的 OAuth 端点如下：

·    临时凭证请求： https: `//www.zotero.org/oauth/request`

·    令牌请求 URI： https: `//www.zotero.org/oauth/access`

·    资源所有者授权 URI： https: `//www.zotero.org/oauth/authorize`

不应使用 OAuth 来签署每个请求，而应使用 OAuth 来获取后续请求的密钥。密钥将无限期有效，除非用户手动撤销它，因此密钥应被视为敏感。但请注意，Zotero API 仅使用 HTTPS 请求，因此普通流量不会暴露密钥。

除了接收令牌之外，使用 OAuth 的 API 使用者还需要从 Zotero.org 返回的响应参数中检索用户的用户 ID。

### 示例（PHP）

此 PHP 脚本演示了与 zotero.org 进行 OAuth 握手的应用程序端的实现，使用由此获得的 API 密钥向 Zotero API 发出请求。

```
/** 请注意，此示例使用 PHP OAuth 扩展 http://php.net/manual/en/book.oauth.php 
* 但有各种 PHP 库提供类似的功能。** OAuth 作用于多个页面，因此我们将需要记住的变量保存在临时文件中的 $state 中** OAuth 握手有 3 个步骤：* 1：向提供者发出请求以获取临时令牌* 2：通过引用临时令牌将用户重定向到提供者令牌。提供商将要求他们授权* 3：当提供商发回用户并且临时令牌获得授权时，将其交换为永久令牌
*然后保存永久令牌以供代表该用户在将来的所有请求中使用。 
** 因此，OAuth 使用者需要处理本示例涵盖的 3 个状态：* 状态 0：我们需要为用户启动新的 OAuth 握手，以授权我们获取他们的信息。* 我们从提供者处获取请求令牌，并发送用户去授权* 状态 1：提供商在授权请求令牌后刚刚将用户发回* 我们使用为该用户存储的请求令牌 + 密钥和提供商刚刚发送回的验证者* 交换请求访问令牌的令牌。*状态 2：我们在过去的握手中为该用户存储了一个访问令牌，因此我们使用它向提供者发出数据请求*。**///初始化一些变量。/ /clientkey、clientSecret 和callbackurl 应对应于http://www.zotero.org/oauth/apps$clientKey = '9c6221a6ccae7639711a';$clientSecret = '39091046dc9cf4dc3b61';$callbackUrl = 'http://localhost/oauthtestentry.php ';//端点特定于 OAuth 提供商，在本例中 Zotero$request_token_endpoint = 'https://www.zotero.org/oauth/request';$access_token_endpoint = 'https://www.zotero.org/ oauth/access';$zotero_authorize_endpoint = 'https://www.zotero.org/oauth/authorize';//在请求之间将状态保存到临时文件的功能，DB应该替换此功能function read_state(){ return unserialize(file_get_contents( '/tmp/oauthteststate'));}function write_state($state){ file_put_contents('/tmp/oauthteststate', serialize($state));}function save_request_token($request_token_info, $state){ // 确认请求token 包含我们需要的所有信息 if(isset($request_token_info['oauth_token']) && isset($request_token_info['oauth_token_secret'])){ // 保存用户返回时的请求令牌 $state['request_token_info' ] = $request_token_info; $state['oauthState'] = 1; write_state($状态); } else{ die("请求令牌没有返回我们需要的所有信息。"); 
} 
}function get_request_token($state){ if($_GET['oauth_token'] != $state['request_token_info']['oauth_token']){ die("找不到引用的 OAuth 请求令牌"); } else{ 返回 $state['request_token_info']; }}function save_access_token($access_token_info, $state){ if(!isset($access_token_info['oauth_token']) || !isset($access_token_info['oauth_token_secret'])){ //访问令牌请求出现问题并且我们没有得到我们需要的信息抛出新的异常（“OAuth访问令牌不包含预期的信息”）; } //我们获得了访问令牌，因此请将其保存以供将来使用 $state['oauthState'] = 2; $state['access_token_info'] = $access_token_info; write_state($状态); //保存所有后续请求的访问令牌，在 Zotero 的情况下，令牌和秘密是相同的 Zotero API 密钥}function get_access_token($state){ if(empty($state['access_token_info'])){ die("无法从存储中检索访问令牌。"); } return $state['access_token_info'];}//初始化我们的环境//检查是否有正在进行的交易//出于测试目的，从一个新的状态开始执行新的握手if(empty($_GET['reset) ']) && file_exists('/tmp/oauthteststate')){ $state = read_state();}else{ $state = array(); $state['localUser'] = 'localUserInformation'; $state['oauthState'] = 0; // 我们还没有处理中的 oauth 事务 write_state($state);}// 如果我们处于 state=1 状态，应该有一个 oauth_token，如果没有则返回 0if($state['oauthState'] == 1 && !isset($_GET['oauth_token'])){ $state['oauthState'] = 0; 
} 
//确保我们已经安装了 OAuth，具体取决于您使用的库if(!class_exists('OAuth')){ die("Class OAuth 不存在。请确保安装并启用 PHP OAuth 扩展。");} //设置一个新的OAuth对象，使用提供者接受的客户端凭据和方法进行初始化$oauth = new OAuth($clientKey, $clientSecret, OAUTH_SIG_METHOD_HMACSHA1, OAUTH_AUTH_TYPE_FORM);$oauth->enableDebug(); //如果出现问题，获取反馈。不应在生产中使用//根据我们所处的状态处理 OAuth 握手的不同部分 switch($state['oauthState']){ case 0: // State 0 - 从 Zotero 获取请求令牌并将用户重定向到Zotero 授权 try{ $request_token_info = $oauth->getRequestToken($request_token_endpoint, $callbackUrl); } catch(OAuthException $E){ echo "获取请求令牌时出现问题<br>";回声 $E->lastResponse;回声“<br>”；死; save_request_token($request_token_info, $state); 

// 将用户发送到提供商以授权您的请求令牌 // 这也可以是用户遵循的链接 $redirectUrl = "{$zotero_authorize_endpoint}?oauth_token={$request_token_info['oauth_token']}"; header('位置：' . $redirectUrl);休息;情况 1: // 状态 1 - 处理来自 Zotero 的回调并获取并存储访问令牌 // 确保我们发回的令牌与我们拥有的令牌相匹配 // 在实践中，我们将查找存储的令牌以及任何本地用户信息我们已将其绑定 $request_token_info = get_request_token($state); //如果我们找到了临时令牌，请尝试将其交换为永久令牌 try{ //设置我们从提供者那里获得的令牌以及我们之前为交换而保存的秘密。 $oauth->setToken($_GET['oauth_token'], $request_token_info['oauth_token_secret']); 
//向提供者的给定端点发出交换请求 $access_token_info = $oauth->getAccessToken($access_token_endpoint); save_access_token($access_token_info, $state); } catch(Exception $e){ //处理获取访问令牌时发生的错误 die("捕获访问令牌请求上的异常"); } // 继续到 switch 之外的授权状态 Break; case 2: //如果我们不是从handshack中获取之前存储的访问令牌，则获取它 $access_token_info = get_access_token($state); break;}// 状态 2 - 已授权。我们已经存储了一个访问令牌，我们可以将其用于代表此用户的请求recho "Have access token for user.";//zotero 也会发送与密钥关联的用户 ID $zoteroUserID = $access_token_info['userID'] ;//现在我们可以像使用 Zotero API 密钥一样使用令牌密钥$zoteroApiKey = $access_token_info['oauth_token_secret'];$feed = file_get_contents("https://api.zotero.org/users/{ $zoteroUserID}/items?limit=1&key={$zoteroApiKey}");var_dump($state);echo "<pre>" 。 htmlentities($feed) 。 "</pre>";/** 将来可能会添加对所有 api 请求的 OAuth 支持*，但目前安全 https 无论如何都提供类似的好处*/
```

### −目录

·    [OAuth 密钥交换](https://www.zotero.org/support/dev/web_api/v3/oauth#oauth_key_exchange)

o  [注册您的应用程序](https://www.zotero.org/support/dev/web_api/v3/oauth#registering_your_application)

o  [请求特定权限](https://www.zotero.org/support/dev/web_api/v3/oauth#requesting_specific_permissions)

o  [使用 OAuth 握手进行密钥交换](https://www.zotero.org/support/dev/web_api/v3/oauth#using_oauth_handshake_for_key_exchange)

§ [示例（PHP）](https://www.zotero.org/support/dev/web_api/v3/oauth#example_php)

dev/web_api/v3/oauth.txt · 最后修改: 2017/11/27 04:04 by bwiernik

·    [旧版本](https://www.zotero.org/support/dev/web_api/v3/oauth?do=revisions)

# Zotero Web API v3 中的更改

[Zotero Web API](https://www.zotero.org/support/dev/web_api/v3/start)版本 3引入了新的全 JSON 响应格式和各种其他更改。虽然 API v3 大部分是向后兼容的，但现有客户端可能需要根据使用情况进行[一些小的调整才能完全兼容。](https://www.zotero.org/support/dev/web_api/v3/changes_from_v2#tldr_for_existing_atom_consumers)

·    新的默认全 JSON 响应格式， `format=json`

o  包含用于单对象请求的单个 JSON 对象和用于多对象请求的对象数组

o  所有单独的对象都包含顶级`密钥`和`版本`属性以及顶级`库`、`链接`和`元`对象。

o  `meta`包含不可编辑的系统生成的属性，例如`createdByUser `/ `lastModifiedByUser `（对于组项）、 `creatorSummary`和`numChildren `。

o  其他特定于 Atom 的提要属性（`标题`、`作者`、`已发布`、`已更新`）已被删除。

o  在`Accept``标头中`发送`application/atom+xml ``的客户端`将继续接收 Atom 响应

·    对于`format=json `， `include=data`已取代 Atom 的`content=json`并且现在是默认模式，其中包含可编辑字段的顶级`数据`对象。与`content``一样`，可以请求附加的逗号分隔类型（例如， `include=data,bib `）。请求的类型作为顶级属性提供。 `content=html`仍然是 Atom 中的默认值。

·    多对象写入现在直接采用 JSON 对象数组，而不是具有包含数组的`items `/ `collections `/ `searchs``属性的对象。`

·    对于写入请求，API 现在接受可编辑的 JSON ( `data `) 或完整的父 JSON 对象，服务器自动提取`数据`对象。后者允许在没有任何编程的情况下执行一些编辑任务。

·    `format=json`中的`parsedDate`属性 `meta`对象以 YYYY-MM-DD 形式给出完整的解析日期，因此客户端不需要复制 Zotero 的日期解析逻辑来获取准确的日期。在 v3 Atom 中， `zapi:parsedDate`取代了`zapi:year `。

·    `zapi:numTags`在 v3 Atom 中被删除，因为它对于可编辑 json 中的`标签数组来说是不必要的。`

·    未提供`限制`，API 现在会为每个请求返回 25 个结果，而不是 50 个。

·    多对象响应的总结果计数可在新的自定义 HTTP 标头`Total-Results``中获得`。 `zapi:totalResults`在 v3 Atom 中被删除。

·    `链接``HTTP ``标头`中提供了用于多对象响应的`rel=first `/ `prev `/ `next `/ `last `/`备用链接`。

·    `授权请求标头`中提供 API 密钥，而不是`密钥`查询参数。由于 API 密钥从未包含在响应中提供的 URL 中，因此之前必须修改所有提供的 URL 以进行基于密钥的访问。

·    API 版本可以作为查询参数 ( `v=3 `) 而不是`Zotero-API-Version`标头提供，以便更轻松地调试和共享请求，但两者仍将受到支持。

·    对于 Atom 以外的格式，默认排序为`dateModified``降序，而不是``dateAdded`降序。

·    `itemKey `/ `itemVersion `（以及集合和搜索上的类似属性）现在只是`键`和`版本`，以便客户端更轻松地处理。仅传回编辑后的 JSON 而不触及这些属性的客户端不应受到影响。存储 JSON 的客户端需要在发送 v3 之前对其进行修改。

·    `ComputerProgram``项类型中的`版本`元`数据字段现在为`versionNumber``，`以避免与重命名的对象版本属性发生冲突。

·    添加日期/修改日期包含在 ISO 8601 形式的“数据”对象中。以前，这些时间戳仅在 Atom`发布`/`更新的元素`中提供，但在 v2 中，它们可以在 JSON 中以YYYY-MM-DD hh-mm-dd 格式（解释为 UTC）作为`dateAdded `/ `dateModified`发送回。在 v3 写入请求中，尽管以前的形式已被弃用，但任一形式都会被接受。

·    accessDate字段以前也是 YYYY-MM-DD[ hh-mm-dd]，在 v3 中（包括在 Atom 中）对于读取和写入来说都是 ISO 8601 `。`先前的形式已被接受但已弃用。

·    使用`授权标头的客户端可以在不修改的情况下使用多对象响应上的`分页链接 ( `rel=self `/ `first `/ `prev `/ `next `/ `last ) `。各个对象中的rel `=self`链接表示基本 URI，不包含任何查询参数（例如， `include=data,bib `）。这与之前的行为有所不同，其中 Atom 条目`rel=“self”`链接包括所有非默认提供的参数。但是，使用`Authorization`标头和`include=data`作为新的默认值，基本 URI 可能足以满足大多数单个对象请求。

·    `为了清楚起见，`现在使用`新的`参数。`较新的`已被弃用。

·    order参数现在是`sort `， `sort ``参数现在`是`Direction `。 `order=<field>`和`sort=<asc/desc>`已弃用。

·    更新组元数据的请求现在可以使用`format=versions`而不是`format=etags `。 `format=etags`已弃用。

·    `pprint=1`已被删除，所有响应现在都已打印精美。

·    '<'、'>' 和 '&' 不再不必要地转义为 \u...。在返回的 JSON 数据中。在 Atom 中，这些字符被转换为 XML 数字字符引用。正确的 XML 和 JSON 解析器不应受到这些更改的影响。

·    HTTP 警告标头可用于向客户端发送可记录的非致命警告（例如弃用警告）。

### tl;dr 对于现有的 Atom 消费者

·    请求`format=atom ``，或`在`Accept`标头中发送`application/atom+xml`

·    使用`zapi:parsedDate`而不是`zapi:year`

·    使用`Total-Results `HTTP 标头而不是`zapi:totalResults`

·    计算可编辑 JSON 中的`标签`数组，而不是使用`zapi:numTags`

·    在可编辑的 JSON 中使用`key `/ `version`而不是`itemKey `/ `itemVersion `（和`collection* `/ `search* ``）`

·    `计算机`程序项类型中使用`版本号`而不是`版本`元数据字段

·    `accessDate `、 `dateAdded`和`dateModified`使用 ISO 8601 日期

·    使用`since`参数而不是`更新的参数`

·    使用`排序`参数代替顺序`，`使用`方向`代替`排序`

·    对于写入，请直接上传 JSON 对象数组，而不是包含`项目`/`集合`/`搜索`数组的对象

·    （可选）使用`Authorization: Bearer <apiKey>`而不是`key`参数

·    或者，使用`v`参数代替`Zotero-API-Version`进行调试

 