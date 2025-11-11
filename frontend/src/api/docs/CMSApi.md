# CMSApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**archive**](CMSApi.md#archive) | **POST** /api/cms/contents/{id}/archive |  |
| [**create9**](CMSApi.md#create9) | **POST** /api/cms/contents |  |
| [**delete9**](CMSApi.md#delete9) | **DELETE** /api/cms/contents/{id} |  |
| [**filter10**](CMSApi.md#filter10) | **POST** /api/cms/contents/filter |  |
| [**getById5**](CMSApi.md#getbyid5) | **GET** /api/cms/contents/{id} |  |
| [**getBySlug**](CMSApi.md#getbyslug) | **GET** /api/cms/contents/slug/{slug} |  |
| [**publish**](CMSApi.md#publish) | **POST** /api/cms/contents/{id}/publish |  |
| [**update9**](CMSApi.md#update9) | **PUT** /api/cms/contents/{id} |  |



## archive

> CustomApiResponseContentResponse archive(id)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { ArchiveRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies ArchiveRequest;

  try {
    const data = await api.archive(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## create9

> CustomApiResponseContentResponse create9(contentRequest)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { Create9Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // ContentRequest
    contentRequest: ...,
  } satisfies Create9Request;

  try {
    const data = await api.create9(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **contentRequest** | [ContentRequest](ContentRequest.md) |  | |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## delete9

> CustomApiResponseVoid delete9(id)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { Delete9Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete9Request;

  try {
    const data = await api.delete9(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filter10

> CustomApiResponsePageContentResponse filter10(searchFilter)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { Filter10Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter10Request;

  try {
    const data = await api.filter10(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageContentResponse**](CustomApiResponsePageContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getById5

> CustomApiResponseContentResponse getById5(id)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { GetById5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById5Request;

  try {
    const data = await api.getById5(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getBySlug

> CustomApiResponseContentResponse getBySlug(slug)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { GetBySlugRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    slug: slug_example,
  } satisfies GetBySlugRequest;

  try {
    const data = await api.getBySlug(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **slug** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## publish

> CustomApiResponseContentResponse publish(id)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { PublishRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies PublishRequest;

  try {
    const data = await api.publish(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## update9

> CustomApiResponseContentResponse update9(id, contentRequest)



### Example

```ts
import {
  Configuration,
  CMSApi,
} from '';
import type { Update9Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new CMSApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // ContentRequest
    contentRequest: ...,
  } satisfies Update9Request;

  try {
    const data = await api.update9(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `string` |  | [Defaults to `undefined`] |
| **contentRequest** | [ContentRequest](ContentRequest.md) |  | |

### Return type

[**CustomApiResponseContentResponse**](CustomApiResponseContentResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

