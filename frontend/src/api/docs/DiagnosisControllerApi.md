# DiagnosisControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addDiagnosis**](DiagnosisControllerApi.md#adddiagnosis) | **POST** /api/examinations/{id}/diagnosis |  |
| [**deleteDiagnosis**](DiagnosisControllerApi.md#deletediagnosis) | **DELETE** /api/examinations/{id}/diagnosis/{diagnosisId} |  |
| [**filterDiagnosis**](DiagnosisControllerApi.md#filterdiagnosis) | **POST** /api/examinations/{id}/diagnosis/filter |  |
| [**getDiagnosisList**](DiagnosisControllerApi.md#getdiagnosislist) | **GET** /api/examinations/{id}/diagnosis |  |



## addDiagnosis

> CustomApiResponseDiagnosisResponse addDiagnosis(id, diagnosisRequest)



### Example

```ts
import {
  Configuration,
  DiagnosisControllerApi,
} from '';
import type { AddDiagnosisRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DiagnosisControllerApi(config);

  const body = {
    // string
    id: id_example,
    // DiagnosisRequest
    diagnosisRequest: ...,
  } satisfies AddDiagnosisRequest;

  try {
    const data = await api.addDiagnosis(body);
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
| **diagnosisRequest** | [DiagnosisRequest](DiagnosisRequest.md) |  | |

### Return type

[**CustomApiResponseDiagnosisResponse**](CustomApiResponseDiagnosisResponse.md)

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


## deleteDiagnosis

> CustomApiResponseVoid deleteDiagnosis(id, diagnosisId)



### Example

```ts
import {
  Configuration,
  DiagnosisControllerApi,
} from '';
import type { DeleteDiagnosisRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DiagnosisControllerApi(config);

  const body = {
    // string
    id: id_example,
    // string
    diagnosisId: diagnosisId_example,
  } satisfies DeleteDiagnosisRequest;

  try {
    const data = await api.deleteDiagnosis(body);
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
| **diagnosisId** | `string` |  | [Defaults to `undefined`] |

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


## filterDiagnosis

> CustomApiResponsePageDiagnosisResponse filterDiagnosis(id, searchFilter)



### Example

```ts
import {
  Configuration,
  DiagnosisControllerApi,
} from '';
import type { FilterDiagnosisRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DiagnosisControllerApi(config);

  const body = {
    // string
    id: id_example,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterDiagnosisRequest;

  try {
    const data = await api.filterDiagnosis(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageDiagnosisResponse**](CustomApiResponsePageDiagnosisResponse.md)

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


## getDiagnosisList

> CustomApiResponseDiagnosisResponse getDiagnosisList(id)



### Example

```ts
import {
  Configuration,
  DiagnosisControllerApi,
} from '';
import type { GetDiagnosisListRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DiagnosisControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetDiagnosisListRequest;

  try {
    const data = await api.getDiagnosisList(body);
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

[**CustomApiResponseDiagnosisResponse**](CustomApiResponseDiagnosisResponse.md)

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

